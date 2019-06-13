package com.thaliees.slidebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SlideButton button;
    private Button reset;
    private TextView text;
    private float movementInitialX, halfToMove;
    private Integer widthButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.slide);
        button.setOnTouchListener(onTouchListener);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(resetButton);
        // Avoid resetting when the sliding button is in your initial state:
        reset.setEnabled(false);
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
                        movementInitialX = button.toSlider.getX();              // Movement initial (when user move for first once the TextView)
                        widthButton = button.getWidth();                        // Get width of our SlideButton
                        button.initWidthToSlider = button.toSlider.getWidth();  // Get width of our TextView (Now, we saved in your property)
                        halfToMove = (float) button.toSlider.getWidth() / 2;    // Calculate half the width of our TextView
                    }
                    // Move the button
                    if (event.getX() > (movementInitialX + halfToMove) && (event.getX() + halfToMove) < widthButton)
                        button.toSlider.setX(event.getX() - halfToMove);
                    // Move to the end and avoid overflowing the limit
                    if ((event.getX() + halfToMove) > widthButton && (button.toSlider.getX() + halfToMove) < widthButton)
                        button.toSlider.setX(widthButton - button.initWidthToSlider);
                    // Move to the start and avoid overflowing the limit
                    if (event.getX() < halfToMove)
                        button.toSlider.setX(0);

                    return true;

                case MotionEvent.ACTION_UP:
                    // What animation to do?
                    if (button.toSlider.getX() == 0) button.collapseButton();
                    else if (button.toSlider.getX() == (widthButton - button.initWidthToSlider)) {
                        text.setVisibility(View.VISIBLE);
                        reset.setEnabled(true);
                        button.expandButton();
                    }
                    else button.moveButtonBack();
            }
            return false;
        }
    };

    private View.OnClickListener resetButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text.setVisibility(View.INVISIBLE);
            reset.setEnabled(false);
            button.collapseButton();
        }
    };
}
