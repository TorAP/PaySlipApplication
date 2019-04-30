package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

public class SupplementCase extends AppCompatActivity {

    TextView textView, textView1, textView2, textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_case);


        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);


        textView.setOnLongClickListener(longClickListener);
        textView1.setOnLongClickListener(longClickListener);
        textView2.setOnLongClickListener(longClickListener);
        textView3.setOnLongClickListener(longClickListener);

        textView3.setOnDragListener(dragListener);


    }

    // A Callback Method, which detects if objects of the View-class has been clicked
    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {

            // Create a new ClipData holding data of the type ClipDescription#MIMETYPE_TEXT_PLAIN.
            ClipData data = ClipData.newPlainText("", "");


            //Constructs a shadow image builder based on the View v .
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);


            //The startDrag-method 
            v.startDrag(data, myShadowBuilder, v, 0);

            return true;

        }


    };

    View.OnDragListener dragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();

            switch (dragEvent) {

                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.textView) {
                        textView3.setText("Jimmy");

                    } else if (view.getId() == R.id.textView1) {
                        textView3.setText("0405021199");

                    } else if (view.getId() == R.id.textView2) {
                        textView3.setText("August");
                    }
                    break;


                case DragEvent.ACTION_DRAG_EXITED:

                    break;

                case DragEvent.ACTION_DROP:
                    break;
            }

            textView.animate()
                    .x(textView3.getX())
                    .y(textView3.getY())
                    .setDuration(10)
                    .start();

            return true;
        }
    };


}
