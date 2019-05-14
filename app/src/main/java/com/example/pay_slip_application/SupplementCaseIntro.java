package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class SupplementCaseIntro extends AppCompatActivity {

    VideoView introVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_information);


        //Get's the size of the screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        //set the window to not fill the entire screen
        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));



        Intent intent = getIntent();
        String videoPath = intent.getStringExtra("VIDEO PATH");

        introVideo = findViewById(R.id.videoView2);
        Uri uri = Uri.parse(videoPath);
        introVideo.setVideoURI(uri);
        introVideo.start();

        MediaController mediaController = new MediaController(this);
        introVideo.setMediaController(mediaController);
        mediaController.setAnchorView(introVideo);


        // Close activity after video has played
        introVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
    }

    public void closeVideo(View view) {
        super.onBackPressed();
    }
}

