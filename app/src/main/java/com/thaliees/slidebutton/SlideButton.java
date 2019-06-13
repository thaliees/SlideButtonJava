package com.thaliees.slidebutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
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
    public Integer initWidthToSlider;

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

    public void expandButton(){
        // Indicate the current point of our TextView is and the point where it should arrive
        final ValueAnimator position = ValueAnimator.ofFloat(toSlider.getX(), 0);
        // Indicate the width of our TextView and how wide it will take
        final ValueAnimator width = ValueAnimator.ofInt(toSlider.getWidth(), getWidth());

        // Update the position of our TextView
        position.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) position.getAnimatedValue();
                toSlider.setX(x);
            }
        });

        // Update the width of our TextView
        width.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParamsToMove = toSlider.getLayoutParams();
                layoutParamsToMove.width = (Integer) width.getAnimatedValue();
                toSlider.setLayoutParams(layoutParamsToMove);
            }
        });

        // Initialize animation
        AnimatorSet animator = new AnimatorSet();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toSlider.setText(R.string.msg_welcome);
                toSlider.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });
        animator.playTogether(position, width);
        //animator.setDuration(2000);           // See animation slow
        animator.start();
    }

    public void moveButtonBack(){
        // Indicate the current point of our TextView is and the point where it should arrive
        final ValueAnimator position = ValueAnimator.ofFloat(toSlider.getX(), 0);
        position.setDuration(2000);  // Also try animator.setDuration(2000);

        // Update the position of our TextView
        position.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) position.getAnimatedValue();
                toSlider.setX(x);
            }
        });

        // Initialize animation
        AnimatorSet animator = new AnimatorSet();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                toSlider.setText(getResources().getString(R.string.msg_ops));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toSlider.setText(R.string.textView_toMove);
            }
        });
        animator.playTogether(position);
        animator.start();
    }

    public void collapseButton(){
        // Indicate the width of our TextView and how wide it will take
        final ValueAnimator width = ValueAnimator.ofInt(toSlider.getWidth(), initWidthToSlider);

        // Update the width of our TextView
        width.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParamsToMove = toSlider.getLayoutParams();
                layoutParamsToMove.width = (Integer) width.getAnimatedValue();
                toSlider.setLayoutParams(layoutParamsToMove);
            }
        });

        // Also can addListener from the ValueAnimator
        width.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                toSlider.setText("");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                toSlider.setText(R.string.textView_toMove);
                toSlider.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });

        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(width);
        animator.start();
    }
}
