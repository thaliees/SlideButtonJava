package com.thaliees.slidebutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideButton extends RelativeLayout {
    public TextView toSlider;

    public SlideButton(Context context) {
        super(context);
        initSlideButton(context);
    }

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSlideButton(context);
    }

    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSlideButton(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SlideButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSlideButton(context);
    }

    private void initSlideButton(Context context){
        RelativeLayout slideButton = new RelativeLayout(context);
        slideButton.setPadding(0, 0, 0, 0);
        RelativeLayout.LayoutParams layoutParamsSlideButton = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsSlideButton.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(slideButton, layoutParamsSlideButton);

        // The TextView will behave like the sliding button
        final TextView toSlider = new TextView(context);
        this.toSlider = toSlider;
        toSlider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        toSlider.setText(getResources().getString(R.string.textView_toMove));
        toSlider.setTextColor(getResources().getColor(R.color.colorWhite));
        toSlider.setTextSize(20);
        toSlider.setTypeface(null, Typeface.BOLD);
        toSlider.setPadding(60, 35, 60, 35);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        slideButton.addView(toSlider, layoutParams);
    }
}
