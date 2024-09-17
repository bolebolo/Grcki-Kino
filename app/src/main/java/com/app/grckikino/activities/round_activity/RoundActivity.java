package com.app.grckikino.activities.round_activity;

import static com.app.grckikino.utils.KeysAndConstants.STATUS_RESULTS;
import static com.app.grckikino.utils.KeysAndConstants.UPCOMING_ROUND_EXTRAS;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.grckikino.R;
import com.app.grckikino.activities.BaseActivity;
import com.app.grckikino.databinding.ActivityRoundBinding;
import com.app.grckikino.models.RoundsModel;
import com.app.grckikino.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class RoundActivity extends BaseActivity {

    private ActivityRoundBinding binding;
    private Snackbar internetSnackbar;
    private NavController navController;
    private RoundActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRoundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RoundActivityViewModel.class);
        viewModel.getRound().observe(this, this::handleUi);

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
        RoundsModel roundsModel = roundBundle.getParcelable(UPCOMING_ROUND_EXTRAS);
        viewModel.setRound(roundsModel);
        Utils.countdownTime(viewModel.getRound());
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                getSupportActionBar().setSubtitle(getString(R.string.round_id, roundsModel.getDrawId()));
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });
    }

    private void handleUi(RoundsModel roundsModel) {
        if (roundsModel.getStatus().equalsIgnoreCase(STATUS_RESULTS)){
            navController.navigate(R.id.navigation_live_draw);
        }
        binding.drawTimeText.setText(getString(R.string.draw_time_, Utils.getTimeString(roundsModel.getDrawTime())));
        long remainingTime = roundsModel.getDrawTime() - System.currentTimeMillis();
        int hours = (int) (remainingTime / (1000 * 60 * 60));
        int minutes = (int) ((remainingTime / (1000 * 60)) % 60);
        int seconds = (int) (remainingTime / 1000) % 60;

        if (hours > 0) {
            binding.drawContdownTime.setText(getString(R.string.countdown_time_hours_format, hours, minutes, seconds));
        } else {
            binding.drawContdownTime.setText(getString(R.string.countdown_time_format, minutes, seconds));
        }
        if (hours * 360 + minutes * 60 + seconds < 10) {
            binding.drawContdownTime.setTextColor(getResources().getColor(R.color.red_800));
            binding.drawContdownTime.setTypeface(null, Typeface.BOLD);
        } else {
            binding.drawContdownTime.setTextColor(getResources().getColor(R.color.black));
            binding.drawContdownTime.setTypeface(null, Typeface.NORMAL);
        }
    }

}