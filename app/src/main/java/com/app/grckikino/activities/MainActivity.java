package com.app.grckikino.activities;

import android.os.Bundle;

import com.app.grckikino.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.grckikino.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private Snackbar internetSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_more_games, R.id.navigation_results).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterNetworkListener();
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

}