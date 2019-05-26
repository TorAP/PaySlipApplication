package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InformationMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_menu);
    }

    // opens the SupplementInformation screen
    public void onClickSupplementInfoButton(View view){
        Intent intent = new Intent(this, SupplementInformation.class);
        startActivity(intent);
    }

    // opens the GeneralInformation screen
    public void onClickGeneralInfoButton(View view){
        Intent intent = new Intent(this, GeneralInformation.class);
        startActivity(intent);
    }

    // goes back to the previous screen
    public void onClickInfoMenuBackButton(View view){
        onBackPressed();
    }
}
