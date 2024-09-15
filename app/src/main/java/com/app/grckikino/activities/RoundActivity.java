package com.app.grckikino.activities;

import static com.app.grckikino.utils.KeysAndConstants.UPCOMING_ROUND_EXTRAS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.grckikino.R;
import com.app.grckikino.databinding.ActivityRoundBinding;
import com.app.grckikino.models.UpcomingRoundsModel;
import com.app.grckikino.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class RoundActivity extends BaseActivity {

    private ActivityRoundBinding binding;
    private Snackbar internetSnackbar;
    private   NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRoundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.round_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_ticket, R.id.navigation_live_draw, R.id.navigation_draw_result).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_round);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.roundNavView, navController);
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkListener();
        handleIntent();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterNetworkListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }

    @Override
    public void onNetworkAvailable() {
        super.onNetworkAvailable();
        if (internetSnackbar != null && internetSnackbar.isShown()) {
            internetSnackbar.dismiss();
        }
    }

    @Override
    public void onNetworkLost() {
        super.onNetworkLost();
        internetSnackbar = internetConnectionSnackbar(this, binding.getRoot());
    }

    private void handleIntent() {
        Bundle roundBundle = getIntent().getBundleExtra(UPCOMING_ROUND_EXTRAS);
        UpcomingRoundsModel roundsModel = roundBundle.getParcelable(UPCOMING_ROUND_EXTRAS);
        binding.drawTimeText.setText(getString(R.string.draw_time_, Utils.getTimeString(roundsModel.getDrawTime())));
//        binding.drawContdownTime.setText(getString(R.string.round_id, roundsModel.getDrawId()));
        Utils.countdownTime(this,  binding.drawContdownTime, roundsModel);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                getSupportActionBar().setSubtitle(getString(R.string.round_id, roundsModel.getDrawId()));
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });
    }

}