package com.app.grckikino.ui_elements;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import com.google.android.material.textview.MaterialTextView;

public class CountdownTextView extends MaterialTextView {

    private CountDownTimer countDownTimer;

    public CountdownTextView(Context context) {
        super(context);
    }

    public CountdownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startCountdown(long drwatTimestemp) {
        stopCountdown();
        long millisInFuture = drwatTimestemp - System.currentTimeMillis();
        if (millisInFuture>0) {
            countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = (millisUntilFinished / 1000) % 60;
                    long minutes = (millisUntilFinished / 1000 / 60) % 60;
                    long hours = (millisUntilFinished / 1000 / 60 / 60);
                    setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                }

                @Override
                public void onFinish() {
                    setText("00:00");
                }
            };
            countDownTimer.start();
        }else{
            setText("00:00");
        }
    }


    public void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}