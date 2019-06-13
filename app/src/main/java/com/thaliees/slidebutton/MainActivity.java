package com.thaliees.slidebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SlideButton button;
    private TextView text;
    private float movementInitialX, halfToMove;
    private Integer widthButton, widthToMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.slide);
        button.setOnTouchListener(onTouchListener);
        text = findViewById(R.id.text);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN: return true;

                case MotionEvent.ACTION_MOVE:
                    // Initialize parameters to evaluate
                    if (movementInitialX == 0) {
                        movementInitialX = button.toSlider.getX();  // Movement initial (when user move for first once the TextView)
                        widthButton = button.getWidth();            // Get width of our SlideButton
                        widthToMove = button.toSlider.getWidth();   // Get width of our TextView
                        halfToMove = (float) widthToMove / 2;       // Calculate half the width of our TextView
                    }
                    // Move the button
                    if (event.getX() > (movementInitialX + halfToMove) && (event.getX() + halfToMove) < widthButton)
                        button.toSlider.setX(event.getX() - halfToMove);
                    // Move to the end and avoid overflowing the limit
                    if ((event.getX() + halfToMove) > widthButton && (button.toSlider.getX() + halfToMove) < widthButton)
                        button.toSlider.setX(widthButton - widthToMove);
                    // Move to the start and avoid overflowing the limit
                    if (event.getX() < halfToMove)
                        button.toSlider.setX(0);

                    return true;

                case MotionEvent.ACTION_UP:
                    // What message show?
                    if (button.toSlider.getX() == 0) text.setText(R.string.msg_helloWorld);
                    else if (button.toSlider.getX() == (widthButton - widthToMove)) text.setText(R.string.msg_welcome);
                    else text.setText(R.string.msg_continue);
            }
            return false;
        }
    };
}
