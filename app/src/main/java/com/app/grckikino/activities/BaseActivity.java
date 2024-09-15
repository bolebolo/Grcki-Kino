package com.app.grckikino.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.app.grckikino.R;
import com.app.grckikino.callbacks.NetworkCallbackListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

public class BaseActivity extends AppCompatActivity implements NetworkCallbackListener {

    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;


    protected void registerNetworkListener() {
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        checkAndInformAboutInternetConnection();
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                runOnUiThread(() -> onNetworkAvailable());
            }

            @Override
            public void onLost(Network network) {
                new CountDownTimer(5000, 1000) {// odbrojavane 5 sekundi pre prikaza da se internet izgubio, moze doci do piucanja neta od jedne sekunde pa da ne uznemiravamo korisnike cestim obavestenjima kada je mreza nestabilna
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (checkInternetConnection()) {
                            cancel();
                        }
                    }

                    @Override
                    public void onFinish() {
                        runOnUiThread(BaseActivity.this::onNetworkLost);
                    }
                }.start();
            }
        };

        if (connectivityManager != null) {
            NetworkRequest.Builder networkRequest = new NetworkRequest.Builder();
            networkRequest.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                networkRequest.addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            }
            connectivityManager.registerNetworkCallback(networkRequest.build(), networkCallback);
        }
    }

    protected void unregisterNetworkListener() {
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    protected boolean checkAndInformAboutInternetConnection() {
        boolean networkStatus = checkInternetConnection();
        if (!networkStatus) {
            onNetworkLost();
        }
        return networkStatus;
    }

    protected boolean checkInternetConnection() {
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }


    @Override
    public void onNetworkAvailable() {}

    @Override
    public void onNetworkLost() {}


    public static Snackbar internetConnectionSnackbar(Activity activity, View parent) {
        Snackbar snackbar = Snackbar.make(parent, "", Snackbar.LENGTH_INDEFINITE);
        View customSnackView = activity.getLayoutInflater().inflate(R.layout.info_banner, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        MaterialTextView bannerMsg = customSnackView.findViewById(R.id.banner_text);
        MaterialButton negativeBtn = customSnackView.findViewById(R.id.negative_btn);
        MaterialButton positiveBtn = customSnackView.findViewById(R.id.positive_btn);
        bannerMsg.setText(activity.getString(R.string.internet_lost_snackbar_message));
        negativeBtn.setVisibility(View.GONE);
        positiveBtn.setText(activity.getString(R.string.settings));
        positiveBtn.setOnClickListener(v -> {
            activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            snackbar.dismiss();
        });
        snackbarLayout.addView(customSnackView, 0);
        View view = snackbar.getView();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        view.setLayoutParams(params);
        snackbar.show();
        return snackbar;
    }
}