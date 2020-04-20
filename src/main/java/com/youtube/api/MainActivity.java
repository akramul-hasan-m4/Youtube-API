package com.youtube.api;


import android.content.Intent;
import android.os.Bundle;

import com.youtube.api.activity.YoutubeActivity;
import com.youtube.api.model.YoutubeVideoModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements YoutubeAdapter.VideoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final List<YoutubeVideoModel> youtubeVideoModelArrayList = generateDummyVidList();
        YoutubeAdapter adapter = new YoutubeAdapter(youtubeVideoModelArrayList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<YoutubeVideoModel> generateDummyVidList() {
        List<YoutubeVideoModel> youtubeVideoModelArrayList = new ArrayList<>();

        YoutubeVideoModel y1 = new YoutubeVideoModel();
        y1.setTitle("4K VIDEO ultrahd hdr sony 4K VIDEOS demo test nature relaxation movie for 4k oled tv");
        y1.setVideoId("Bey4XXJAqS8");

        YoutubeVideoModel y2 = new YoutubeVideoModel();
        y2.setTitle("Relaxing Music with Beautiful Nature - Peaceful Piano & Guitar Music by Soothing Relaxation");
        y2.setVideoId("DGQwd1_dpuc");

        YoutubeVideoModel y3 = new YoutubeVideoModel();
        y3.setTitle("Beautiful Piano Music 24/7 • Relax, Study, Sleep, Work, Meditate");
        y3.setVideoId("y7e-GC6oGhg");

        YoutubeVideoModel y4 = new YoutubeVideoModel();
        y4.setTitle("Beautiful Nature Around The World");
        y4.setVideoId("6lt2JfJdGSY");

        YoutubeVideoModel y5 = new YoutubeVideoModel();
        y5.setTitle("Singing nightingale. The best bird song.");
        y5.setVideoId("XdlIbNrki5o");

        YoutubeVideoModel y6 = new YoutubeVideoModel();
        y6.setTitle("মানুষের মত কথা বলতে পারা স্মার্ট পাখি,প্রথম দেখতে চলেছেন| Top 10 Smartest Talking Birds In The World");
        y6.setVideoId("wS1XqllLOIU");

        youtubeVideoModelArrayList.add(y1);
        youtubeVideoModelArrayList.add(y2);
        youtubeVideoModelArrayList.add(y3);
        youtubeVideoModelArrayList.add(y4);
        youtubeVideoModelArrayList.add(y5);
        youtubeVideoModelArrayList.add(y6);

        return youtubeVideoModelArrayList;
    }

    @Override
    public void onVideoClick(String videoId) {
        startActivity(new Intent(MainActivity.this, YoutubeActivity.class).putExtra("video_id", videoId));
    }
}
