package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }

    public void InfoMenuButton(View view){
        Intent intent = new Intent(this, InformationMenu.class);
        startActivity(intent);


    }
    public void CaseMenuButton(View view){
        Intent intent = new Intent(this, CaseMenu.class);
        startActivity(intent);

    }

}
