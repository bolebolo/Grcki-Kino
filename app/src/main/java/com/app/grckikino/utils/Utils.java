package com.app.grckikino.utils;

import static com.app.grckikino.utils.KeysAndConstants.DATE_FORMAT;
import static com.app.grckikino.utils.KeysAndConstants.QUOTA_FORMAT;
import static com.app.grckikino.utils.KeysAndConstants.STATUS_RESULTS;
import static com.app.grckikino.utils.KeysAndConstants.TIME_FORMAT;
import static com.app.grckikino.utils.KeysAndConstants.TWO_DECIMAL_FORMAT;

import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import com.app.grckikino.R;
import com.app.grckikino.models.TicketNumberModel;
import com.app.grckikino.models.RoundsModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getTimeString(long timestamp) {
        SimpleDateFormat sdformat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return sdformat.format(new Date(timestamp));
    }

    public static String getDateString(long timestamp) {
        SimpleDateFormat sdformat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return sdformat.format(new Date(timestamp));
    }

    public static int getSelectedItemCount(ArrayList<TicketNumberModel> ticketNumbersList) {
        int selectedCount = 0;
        for (TicketNumberModel item : ticketNumbersList) {
            if (item.getIsSelected() == 1) {
                selectedCount++;
            }
        }
        return selectedCount;
    }
    public static double calculateQuota(int ballNumb){ // neka odokativna  Formula: K(L) ≈ 3.75 × 4^(L-1)
        if(ballNumb==0){
          return 0;
        }
        double baseQuota = 3.75;
        double exponent = ballNumb -1;
        return baseQuota * Math.pow(4, exponent);
    }

    public static String formatNumber(int number) {
        String formatted = String.format(Locale.getDefault(), TWO_DECIMAL_FORMAT,  calculateQuota(number));
        DecimalFormat decimalFormat = new DecimalFormat(QUOTA_FORMAT);
        return decimalFormat.format(Double.parseDouble(formatted));
    }

    public static int convertDpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void countdownTime(MutableLiveData<RoundsModel> liveData) {
        RoundsModel roundsModel = liveData.getValue();
        if (roundsModel != null) {
            long remainingTime = roundsModel.getDrawTime() - System.currentTimeMillis();
            new CountDownTimer(remainingTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    liveData.setValue(roundsModel);
                }
                @Override
                public void onFinish() {
                    roundsModel.setStatus(STATUS_RESULTS);
                    //Todo ovde mozemo napraviti da se prebaci na fragment livedraw i zabrani pristup popunjavanju listica ako je isteklo vreme
                }
            }.start();
        }
    }

    public static int getTextColorOnSurface(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(com.google.android.material.R.attr.colorOnSurface, typedValue, true);
        return typedValue.data;
    }

    public static int getColorForNumber(Context context, int number) {
        if (number >= 1 && number <= 10) {
            return ContextCompat.getColor(context, R.color.balls_color_1_10);
        } else if (number >= 11 && number <= 20) {
            return ContextCompat.getColor(context, R.color.balls_color_11_20);
        } else if (number >= 21 && number <= 30) {
            return ContextCompat.getColor(context, R.color.balls_color_21_30);
        } else if (number >= 31 && number <= 40) {
            return ContextCompat.getColor(context, R.color.balls_color_31_40);
        } else if (number >= 41 && number <= 50) {
            return ContextCompat.getColor(context, R.color.balls_color_41_50);
        } else if (number >= 51 && number <= 60) {
            return ContextCompat.getColor(context, R.color.balls_color_51_60);
        } else if (number >= 61 && number <= 70) {
            return ContextCompat.getColor(context, R.color.balls_color_61_70);
        } else if (number >= 71 && number <= 80) {
            return ContextCompat.getColor(context, R.color.balls_color_71_80);
        } else {
            throw new IllegalArgumentException("Broj mora biti između 1 i 80");
        }
    }
}
