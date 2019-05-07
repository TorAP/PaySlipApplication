package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class SupplementInformation extends AppCompatActivity {

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



        VideoView supplementVideo = findViewById(R.id.videoView2);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.supplementvideo;
        Uri uri = Uri.parse(videoPath);
        supplementVideo.setVideoURI(uri);
        supplementVideo.start();

        MediaController mediaController = new MediaController(this);
        supplementVideo.setMediaController(mediaController);
        mediaController.setAnchorView(supplementVideo);
    }
}
