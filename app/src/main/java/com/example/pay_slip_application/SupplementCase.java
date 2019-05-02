package com.example.pay_slip_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SupplementCase extends AppCompatActivity {

    TextView dragger1, dragger2, dragger3, dropper1, dropper2, dropper3;
    private static String DRAGGER_TAG = "Drag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplement_case);


        dragger1 = (TextView) findViewById(R.id.dragName);
        dragger1.setTag(DRAGGER_TAG);
        dragger2 = (TextView) findViewById(R.id.dragCpr);
        dragger2.setTag(DRAGGER_TAG);
        dragger3 = (TextView) findViewById(R.id.dragMonth);
        dragger3.setTag(DRAGGER_TAG);

        dropper1 = (TextView) findViewById(R.id.dropName);
        dropper2 = (TextView) findViewById(R.id.dropCpr);
        dropper3 = (TextView) findViewById(R.id.dropMonth);


        dragger1.setOnLongClickListener(longClickListener);
        dragger2.setOnLongClickListener(longClickListener);
        dragger3.setOnLongClickListener(longClickListener);
        dropper1.setOnLongClickListener(longClickListener);
        dropper2.setOnLongClickListener(longClickListener);
        dropper3.setOnLongClickListener(longClickListener);

        dragger1.setOnDragListener(dragListener);
        dragger2.setOnDragListener(dragListener);
        dragger3.setOnDragListener(dragListener);
        dropper1.setOnDragListener(dragListener);
        dropper2.setOnDragListener(dragListener);
        dropper3.setOnDragListener(dragListener);


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

    View.OnDragListener dragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();

            switch (action) {

                case DragEvent.ACTION_DRAG_STARTED:
                    View srcView = (View) event.getLocalState();
                    if (((TextView) v).getText() == "" && (srcView.getTag() != DRAGGER_TAG || srcView.getTag() != v.getTag())) {
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                            v.setBackgroundColor(Color.YELLOW);

                            return true;
                        }
                    }
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    if (((TextView) v).getText() == "") {
                        v.setBackgroundColor(Color.GREEN);
                    }

                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.YELLOW);

                    return true;

                case DragEvent.ACTION_DROP:
                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);


                    // Makes sure text can't be dropped if there already is text.
                    if (((TextView) v).getText() == "") {
                        // Get dragged view object from drag event object.
                        View view = (View) event.getLocalState();
                        ((TextView) view).setText("");
                        ((TextView) v).setText(item.getText());

                        return true;

                    }
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
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


}
