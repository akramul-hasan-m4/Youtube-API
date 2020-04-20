package com.youtube.api;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.youtube.api.model.YoutubeVideoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.youtube.api.activity.YoutubeActivity.API_KEY;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder> {

    private static final String TAG = "YoutubeAdapter";
    private List<YoutubeVideoModel> youtubeVideoModelArrayList;
    private VideoListener listener;

    public interface VideoListener{
        void onVideoClick(String videoId);
    }

    public YoutubeAdapter(List<YoutubeVideoModel> youtubeVideoModelArrayList, VideoListener listener) {
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent , false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int position) {
        final YoutubeVideoModel youtubeVideoModel = youtubeVideoModelArrayList.get(position);
        holder.videoTitle.setText(youtubeVideoModel.getTitle());

        holder.videoThumbnailImageView.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(youtubeVideoModel.getVideoId());

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");
            }
        });
        holder.videoThumbnailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVideoClick(youtubeVideoModel.getVideoId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList == null ? 0 : youtubeVideoModelArrayList.size();
    }

    class YoutubeViewHolder extends RecyclerView.ViewHolder{

         YouTubeThumbnailView videoThumbnailImageView;
         TextView videoTitle;

         YoutubeViewHolder(View itemView) {
            super(itemView);
            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            videoTitle = itemView.findViewById(R.id.video_title_label);
        }
    }
}
