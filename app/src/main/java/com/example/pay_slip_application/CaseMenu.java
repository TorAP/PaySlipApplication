package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CaseMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_menu);
    }

    // opens the OverviewCase screen
    public void onClickCase(View view) {
           Intent intent = new Intent(this, OverviewCase.class);
           startActivity(intent);
    }

    // Goes back to the MainActivity screen
    public void onClickBack(View view) {
        onBackPressed();
    }

    // Goes back to the MainActivity screen when the phone's back button is pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
