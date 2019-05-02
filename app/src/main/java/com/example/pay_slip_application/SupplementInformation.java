package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class SupplementInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_information);

        VideoView supplementVideo = findViewById(R.id.videoView2);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.supplementvideo;
        Uri uri = Uri.parse(videoPath);
        supplementVideo.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        supplementVideo.setMediaController(mediaController);
        mediaController.setAnchorView(supplementVideo);
    }
    public void onClickSupplementInformationBackButton(View view){
        Intent intent = new Intent(this, InformationMenu.class); //Info menu class
        startActivity(intent);
    }
}
