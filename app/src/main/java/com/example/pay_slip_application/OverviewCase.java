package com.example.pay_slip_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OverviewCase extends AppCompatActivity {

    TextView dragger1, dragger2, dragger3, dropper1, dropper2, dropper3;
    // tag used to identify if a TextView is a "dragger", meaning containing the text that should be placed on the pay slip
    public static String DRAGGER_TAG = "Drag";
    // used for identifying the video path for the OverviewCaseIntro screen
    public static final String EXTRA_VIDEO_PATH = "VIDEO PATH";
    // String used for toast message
    public final String TOAST_MESSAGE = "Du har lavet en fejl. Prøv igen";


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_case);

        dragger1 = (TextView) findViewById(R.id.dragName);
        dragger1.setTag(DRAGGER_TAG);
        dragger2 = (TextView) findViewById(R.id.dragCpr);
        dragger2.setTag(DRAGGER_TAG);
        dragger3 = (TextView) findViewById(R.id.dragMonth);
        dragger3.setTag(DRAGGER_TAG);

        dropper1 = (TextView) findViewById(R.id.dropName);
        dropper2 = (TextView) findViewById(R.id.dropCpr);
        dropper3 = (TextView) findViewById(R.id.dropMonth);


        // setting every TextView as OnTouchListeners
        dragger1.setOnTouchListener(onTouchListener);
        dragger2.setOnTouchListener(onTouchListener);
        dragger3.setOnTouchListener(onTouchListener);
        dropper1.setOnTouchListener(onTouchListener);
        dropper2.setOnTouchListener(onTouchListener);
        dropper3.setOnTouchListener(onTouchListener);

        // setting every TextView as OnDragListeners
        dragger1.setOnDragListener(dragListener);
        dragger2.setOnDragListener(dragListener);
        dragger3.setOnDragListener(dragListener);
        dropper1.setOnDragListener(dragListener);
        dropper2.setOnDragListener(dragListener);
        dropper3.setOnDragListener(dragListener);

        // opening OverviewCaseIntro screen
        showIntro();
    }

    // An interface which detects if objects of the View-class has been clicked
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && ((TextView) v).getText() != "") {
                ClipData.Item item = new ClipData.Item(((TextView) v).getText().toString());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                // clipdata copies the text from the touched TextView
                ClipData data = new ClipData(((TextView) v).getText(), mimeTypes, item);


                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                // start the drag and drop with the touched TextView
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;
            }
        }
    };

    // Callback method that detects different actions of a view being dragged
    View.OnDragListener dragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();

            // beginning switch statement
            switch (action) {

                // Controls what happens when a drag is started.
                case DragEvent.ACTION_DRAG_STARTED:
                    View srcView = (View) event.getLocalState();

                    // background color is changed for TextViews other than the dragged TextView, that do not have the dragger tag
                    if ((srcView.getTag() != DRAGGER_TAG || srcView.getTag() != v.getTag())) {
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                            // changing background color of the dragListeners
                            v.setBackgroundColor(getColor(R.color.colorDropZone));

                            return true;
                        }
                    }
                    return false;

                // Controls what happens when a drag has entered the boundaries of a OnDragListener
                case DragEvent.ACTION_DRAG_ENTERED:
                    // changes background color of the drag listeners
                    v.setBackgroundColor(getColor(R.color.colorEnterDrop));

                    return true;

                // Controls what happens when the dragged item is dragged away from the OnDragListener's boundaries
                case DragEvent.ACTION_DRAG_EXITED:
                    // changes background color of the drag listeners
                    v.setBackgroundColor(getColor(R.color.colorDropZone));

                    return true;

                // Controls what happens when a dragged view is dropped on a OnDragListener
                case DragEvent.ACTION_DROP:

                    // Get dragged view object from drag event object.
                    View draggedView = (View) event.getLocalState();
                    String draggedText = ((TextView) draggedView).getText().toString();
                    String targetText = ((TextView) v).getText().toString();

                    // Set text of the dragged view to the text of the target view
                    ((TextView) draggedView).setText(targetText);
                    // Set text of the target view to the text from the dragged view
                    ((TextView) v).setText(draggedText);

                    return true;


                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                // Resets color when drag is ended
                case DragEvent.ACTION_DRAG_ENDED:

                    v.setBackgroundColor(getColor(R.color.colorLightGrey));


                    // returns true; the value is ignored.
                    return true;
            }
            return false;
        }

    };


    // opens GeneralInformation screen
    public void onClickInfo(View view) {
        Intent intent = new Intent(this, GeneralInformation.class);
        startActivity(intent);
    }

    // if all text is placed correctly with drag and drop the OverviewCasePart2 screen is opened
    public void onClickContinue(View view) {
        if (checkIfPlacedCorrect(dropper1, dropper2, dropper3)) {
            Intent intent = new Intent(this, OverviewCasePart2.class);
            startActivity(intent);
        } else {
            //Makes a toast telling the user they made a mistake
            Toast.makeText(getApplicationContext(), TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
        }
    }

    // opens OverviewCaseIntro screen. Transfers the video path with the intent
    public void showIntro() {
        Intent intent = new Intent(this, OverviewCaseIntro.class);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.introvideo_part1;
        intent.putExtra(EXTRA_VIDEO_PATH, videoPath);
        startActivity(intent);
    }


    //opens the Alert dialog
    public void onClickAlert(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OverviewCase.this);

        builder.setTitle("Afslut case");

        builder.setMessage("Er du sikker på, at du vil afslutte? Dine fremskridt vil ikke blive gemt");

        builder.setIcon(R.drawable.alert_iconmdpi);

        builder.setCancelable(true);

        // positive button. When this is pressed the CaseMenu screen or the MainActivity screen is opened, depending on the button pressed
        builder.setPositiveButton("Ja, afslut", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
                ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
                if (view == backButton) {
                    Intent intent = new Intent(getApplicationContext(), CaseMenu.class);
                    startActivity(intent);
                }
                if (view == homeButton) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        // negative button. Cancels and closes the alert dialog
        builder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }

    // calls showIntro method
    public void onClickIntro(View view) {
        showIntro();
    }

    // takes the dropper TextViews as arguments and checks if their value are correct
    public boolean checkIfPlacedCorrect(TextView firstView, TextView secondView, TextView thirdView) {
        //Checks if the Strings in the Drop areas are correct
        if (firstView.getText().equals(getResources().getString(R.string.name)) && secondView.getText().equals(getResources().getString(R.string.cpr))
                && thirdView.getText().equals(getResources().getString(R.string.month))) {
            return true;
        } else {
            return false;
        }
    }

    // overrides the phone's back button so nothing happens when pressed.
    @Override
    public void onBackPressed() {

    }
}
