package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SupplementCase extends AppCompatActivity {

    TextView textView,textView1,textView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_case);


        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        textView.setOnClickListener(longClickListener);
        textView1.setOnClickListener(longClickListener);
        textView2.setOnClickListener(longClickListener);


    }


    View.OnClickListener longClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);

        }
    };











}
