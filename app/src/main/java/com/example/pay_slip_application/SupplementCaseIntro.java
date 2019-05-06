package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class SupplementCaseIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_case_intro);

        //Get's the size of the screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        //set the window to not fill the entire screen
        getWindow().setLayout((int)(width*0.8),(int)(height*0.7));

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");

        TextView textView = (TextView) findViewById(R.id.supplementCaseIntroText);
        textView.setText(message);
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }
}

