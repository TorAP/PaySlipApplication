package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int backButtonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }

        // resetting backButtonCount whenever the MainActivity is opened
    @Override
    protected void onResume() {
        super.onResume();
        backButtonCount = 0;
    }

    // opens the InformationMenu screen
    public void InfoMenuButton(View view){
       Intent intent = new Intent(this, InformationMenu.class);
       startActivity(intent);
    }

    // opens the CaseMenu screen
    public void CaseMenuButton(View view){
        Intent intent = new Intent(this, CaseMenu.class);
        startActivity(intent);
    }

    // Overrides what happens when the back button on the phone is pressed. Closes the application when the back button has been pressed twice.
    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Tryk på tilbageknappen igen for at lukke", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
