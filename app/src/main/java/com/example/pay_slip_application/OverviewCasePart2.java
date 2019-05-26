package com.example.pay_slip_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// subclass to OverviewCase
public class OverviewCasePart2 extends OverviewCase {

    TextView dragger4, dropper4;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_case_part2);


        dragger1 = (TextView) findViewById(R.id.dragArbBidrag);
        dragger1.setTag(DRAGGER_TAG);
        dragger2 = (TextView) findViewById(R.id.dragAIndkomst);
        dragger2.setTag(DRAGGER_TAG);
        dragger3 = (TextView) findViewById(R.id.dragLoensats);
        dragger3.setTag(DRAGGER_TAG);
        dragger4 = (TextView) findViewById(R.id.dragASkat);
        dragger4.setTag(DRAGGER_TAG);

        dropper1 = (TextView) findViewById(R.id.dropArbBidrag);
        dropper2 = (TextView) findViewById(R.id.dropAIndkomst);
        dropper3 = (TextView) findViewById(R.id.dropLoensats);
        dropper4 = (TextView) findViewById(R.id.dropASkat);

        // setting every TextView as OnTouchListeners
        dragger1.setOnTouchListener(onTouchListener);
        dragger2.setOnTouchListener(onTouchListener);
        dragger3.setOnTouchListener(onTouchListener);
        dragger4.setOnTouchListener(onTouchListener);
        dropper1.setOnTouchListener(onTouchListener);
        dropper2.setOnTouchListener(onTouchListener);
        dropper3.setOnTouchListener(onTouchListener);
        dropper4.setOnTouchListener(onTouchListener);

        // setting every TextView as OnDragListeners
        dragger1.setOnDragListener(dragListener);
        dragger2.setOnDragListener(dragListener);
        dragger3.setOnDragListener(dragListener);
        dragger4.setOnDragListener(dragListener);
        dropper1.setOnDragListener(dragListener);
        dropper2.setOnDragListener(dragListener);
        dropper3.setOnDragListener(dragListener);
        dropper4.setOnDragListener(dragListener);

    }

    // overrides the method from superclass
    @Override
    public void onClickInfo(View view) {
        super.onClickInfo(view);
    }

    // overrides the method from superclass
    @Override
    public void onClickAlert(View view) {
        super.onClickAlert(view);
    }

    // opens OverviewCaseIntro screen. Transfers the needed video path with the intent
    public void showIntro() {
        Intent intent = new Intent(this, OverviewCaseIntro.class);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.introvideo_part2;
        intent.putExtra(EXTRA_VIDEO_PATH, videoPath);
        startActivity(intent);
    }

    // overrides the method from superclass
    @Override
    public void onClickIntro(View view) {
        super.onClickIntro(view);
    }

    // if all text is placed correctly with drag and drop the OverviewCasePart3 screen is opened
    @Override
    public void onClickContinue(View view) {
        if (checkIfPlacedCorrect(dropper1, dropper2, dropper3, dropper4)) {
            Intent intent = new Intent(this, OverviewCasePart3.class);
            startActivity(intent);
        } else {
            //Makes a toast telling the user they made a mistake
            Toast.makeText(getApplicationContext(), TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    // takes the dropper TextViews as arguments and checks if their value are correct
    public boolean checkIfPlacedCorrect(TextView firstView, TextView secondView, TextView thirdView, TextView fourthView) {
        if (firstView.getText().equals(getResources().getString(R.string.ArbBidrag))
                && secondView.getText().equals(getResources().getString(R.string.aIndkomstButtonText))
                && thirdView.getText().equals(getResources().getString(R.string.LoensatsButtonText))
                && fourthView.getText().equals(getResources().getString(R.string.aSkatButtonText))) {
            return true;
        } else {
            return false;
        }
    }
}
