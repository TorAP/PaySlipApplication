package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SupplementCasePart3 extends SupplementCasePart2 {


    private static String DRAGGER_TAG = "Drag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_case_part3);

        INTRO_TEXT_SIZE = 20;

        dragger1 = (TextView) findViewById(R.id.dragLoensats);
        dragger1.setTag(DRAGGER_TAG);
        dragger2 = (TextView) findViewById(R.id.dragLoentillæg);
        dragger2.setTag(DRAGGER_TAG);


        dropper1 = (TextView) findViewById(R.id.dropLoensats);
        dropper2 = (TextView) findViewById(R.id.dropLoentillæg);

        dragger1.setOnLongClickListener(longClickListener);
        dragger2.setOnLongClickListener(longClickListener);

        dropper1.setOnDragListener(dragListener);
        dropper2.setOnDragListener(dragListener);


    }

    public void onClickInfo(View view) {
        Intent intent = new Intent(this, SupplementInformation.class);
        startActivity(intent);
    }

    @Override
    public void onClickIntro(View view) {
        Intent intent = new Intent(this, SupplementCaseIntro.class);
        String message = getResources().getString(R.string.supplementCaseIntro3);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onClickAlert(View view) {
        super.onClickAlert(view);
    }

    @Override
    public void onClickContinue(View view) {
        if (checkIfPlacedCorrect(dropper1, dropper2)) {
            Intent intent = new Intent(this, SupplementCasePart3.class);
            startActivity(intent);
        } else {
            //Makes a toast telling the user they made a mistake
            Toast.makeText(getApplicationContext(), TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkIfPlacedCorrect(TextView firstView, TextView secondView) {
        if (firstView.getText().equals(getResources().getString(R.string.loenSatsButton2))
                && secondView.getText().equals(getResources().getString(R.string.loenTillaegButton))) {
            return true;
        } else {
            return false;
        }
    }
}
