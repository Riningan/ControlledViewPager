package com.riningan.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Vadim Akhmarov on 07.12.2018.
 * Project ControlledViewPager
 * Classname ControlledViewPager
 * Version 1.0
 * Copyright All rights reserved.
 */

public class ControlledViewPager extends ViewPager {
    public enum SwipeDirection {
        All, LEFT, RIGHT, NONE
    }


    private float mInitialXValue = 0.0f;
    private SwipeDirection mDirection = SwipeDirection.All;


    public ControlledViewPager(@NonNull Context context) {
        super(context);
    }

    public ControlledViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isSwipeAllowed(event)) {
            try {
                return super.onTouchEvent(event);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isSwipeAllowed(event)) {
            try {
                return super.onInterceptTouchEvent(event);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }


    public void setAllowedSwipeDirection(SwipeDirection direction) {
        mDirection = direction;
    }


    private boolean isSwipeAllowed(MotionEvent event) {
        switch (mDirection) {
            case All:
                return true;
            case NONE:
                //disable any swipe
                return false;
            default:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mInitialXValue = event.getX();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float diffX = event.getX() - mInitialXValue;
                        if (diffX > 0 && mDirection == SwipeDirection.RIGHT) {
                            // swipe from left to right detected
                            return false;
                        } else if (diffX < 0 && mDirection == SwipeDirection.LEFT) {
                            // swipe from right to left detected
                            return false;
                        } else {
                            return true;
                        }
                    default:
                        return true;
                }
        }
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ControlledViewPager, 0, 0);
        int state = typedArray.getInt(R.styleable.ControlledViewPager_swipe_direction, 0);
        mDirection = SwipeDirection.values()[state];
        typedArray.recycle();
    }
}
