package com.xiamenTourism.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.xiamenTourism.R;

public class VideoViewActivity extends AppCompatActivity {
    VideoView video_view;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);


        video_view = findViewById(R.id.video_view);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.intro;
        video_view.setVideoURI(Uri.parse(path));
        mediaController = new MediaController(this);
        video_view.setMediaController(mediaController);
        video_view.start();
    }

    @Override
    public void onBackPressed() {


        MediaController ctrl = new MediaController(this);
        ctrl.setVisibility(View.GONE);
        video_view.setMediaController(ctrl);
        if (video_view.isPlaying()) {
            video_view.pause();
        }
        finish();

    }
}