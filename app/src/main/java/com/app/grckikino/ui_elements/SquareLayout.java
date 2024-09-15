package com.app.grckikino.ui_elements;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class SquareLayout extends FrameLayout {

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, width);//setujemo visinu da bude kao sirina
    }

}
