package com.example.pay_slip_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// subclass to OverviewCasePart2
public class OverviewCasePart3 extends OverviewCasePart2 {


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_case_part3);


        dragger1 = (TextView) findViewById(R.id.dragLoensats);
        dragger1.setTag(DRAGGER_TAG);
        dragger2 = (TextView) findViewById(R.id.dragLoentillæg);
        dragger2.setTag(DRAGGER_TAG);


        dropper1 = (TextView) findViewById(R.id.dropLoensats);
        dropper2 = (TextView) findViewById(R.id.dropLoentillæg);

        // setting every TextView as OnTouchListeners
        dragger1.setOnTouchListener(onTouchListener);
        dragger2.setOnTouchListener(onTouchListener);
        dropper1.setOnTouchListener(onTouchListener);
        dropper2.setOnTouchListener(onTouchListener);

        // setting every TextView as OnDragListeners
        dragger1.setOnDragListener(dragListener);
        dragger2.setOnDragListener(dragListener);
        dropper1.setOnDragListener(dragListener);
        dropper2.setOnDragListener(dragListener);


    }

    // overrides the method from superclass
    @Override
    public void onClickInfo(View view) {
        super.onClickInfo(view);
    }

    // overrides the method from superclass
    @Override
    public void onClickIntro(View view) {
        super.onClickIntro(view);
    }

    // opens OverviewCaseIntro screen. Transfers the needed video path with the intent
    @Override
    public void showIntro() {
        Intent intent = new Intent(this, OverviewCaseIntro.class);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.introvideo_part3;
        intent.putExtra(EXTRA_VIDEO_PATH, videoPath);
        startActivity(intent);
    }

    // overrides the method from superclass
    @Override
    public void onClickAlert(View view) {
        super.onClickAlert(view);
    }

    // if all text is placed correctly with drag and drop the CaseMenu screen is opened
    @Override
    public void onClickContinue(View view) {
        if (checkIfPlacedCorrect(dropper1, dropper2)) {
            Intent intent = new Intent(this, CaseMenu.class);
            startActivity(intent);
        } else {
            //Makes a toast telling the user they made a mistake
            Toast.makeText(getApplicationContext(), TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    // takes the dropper TextViews as arguments and checks if their value are correct
    public boolean checkIfPlacedCorrect(TextView firstView, TextView secondView) {
        if (firstView.getText().equals(getResources().getString(R.string.LoensatsButtonText))
                && secondView.getText().equals(getResources().getString(R.string.loenTillaegButton))) {
            return true;
        } else {
            return false;
        }
    }
}
