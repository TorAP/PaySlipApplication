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

public class SupplementCasePart2 extends SupplementCase {

    TextView dragger4, dropper4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_case_part2);


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


        dragger1.setOnLongClickListener(longClickListener);
        dragger2.setOnLongClickListener(longClickListener);
        dragger3.setOnLongClickListener(longClickListener);
        dragger4.setOnLongClickListener(longClickListener);
        dropper1.setOnLongClickListener(longClickListener);
        dropper2.setOnLongClickListener(longClickListener);
        dropper3.setOnLongClickListener(longClickListener);
        dropper4.setOnLongClickListener(longClickListener);


        dragger1.setOnDragListener(dragListener);
        dragger2.setOnDragListener(dragListener);
        dragger3.setOnDragListener(dragListener);
        dragger4.setOnDragListener(dragListener);
        dropper1.setOnDragListener(dragListener);
        dropper2.setOnDragListener(dragListener);
        dropper3.setOnDragListener(dragListener);
        dropper4.setOnDragListener(dragListener);

    }

    // A Callback Method, which detects if objects of the View-class has been clicked
    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {

            if (((TextView) v).getText() != "") {
                ClipData.Item item = new ClipData.Item(((TextView) v).getText().toString());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};


                ClipData data = new ClipData(((TextView) v).getText(), mimeTypes, item);

                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

                v.startDragAndDrop(data, myShadow, v, 0);

                //v.setVisibility(View.INVISIBLE);
                return true;
            } else return false;
        }


    };
    // Callback method that detects different actions of a view being dragged
    View.OnDragListener dragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();

            switch (action) {

                // Controls what happens when a drag is started.
                case DragEvent.ACTION_DRAG_STARTED:
                    View srcView = (View) event.getLocalState();
                    if (((TextView) v).getText() == "" && (srcView.getTag() != DRAGGER_TAG || srcView.getTag() != v.getTag())) {
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                            v.setBackgroundColor(Color.YELLOW);

                            return true;
                        }
                    }
                    return false;

                // Controls what happens when a drag has entered the boundaries of a OnDragListener
                case DragEvent.ACTION_DRAG_ENTERED:
                    if (((TextView) v).getText() == "") {
                        v.setBackgroundColor(Color.GREEN);
                    }

                    return true;

                // Controls what happens when the dragged item is dragged away from the OnDragListener's boundaries
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.YELLOW);

                    return true;

                // Controls what happens when a dragged view is dropped on a OnDragListener
                case DragEvent.ACTION_DROP:
                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);


                    // Makes sure text can't be dropped if there already is text.
                    if (((TextView) v).getText() == "") {
                        // Get dragged view object from drag event object.
                        View view = (View) event.getLocalState();
                        // Delete text of the dragged view
                        ((TextView) view).setText("");
                        // Set text of the OnDragListener to the dragged data (the text from the dragged view)
                        ((TextView) v).setText(item.getText());

                        return true;

                    }
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                // Resets color when drag is ended
                case DragEvent.ACTION_DRAG_ENDED:

                    v.setBackgroundColor(Color.WHITE);


                    // returns true; the value is ignored.
                    return true;
            }
            return false;
        }

            /*textView.animate()
                    .x(textView3.getX())
                    .y(textView3.getY())
                    .setDuration(10)
                    .start();*/

    };

    public void onClickInfo(View view) {
        Intent intent = new Intent(this, SupplementInformation.class);
        startActivity(intent);
    }

    @Override
    public void onClickAlert(View view) {
        super.onClickAlert(view);
    }

    public void showIntro() {
        Intent intent = new Intent(this, SupplementCaseIntro.class);
        String message = getResources().getString(R.string.supplementCaseIntro2);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}
