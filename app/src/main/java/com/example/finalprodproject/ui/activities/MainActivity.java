package com.example.finalprodproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ActivityMainBinding;
import com.example.finalprodproject.feature_main.presentation.services.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NetworkChangeReceiver networkChangeReceiver;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initNavigation();
    }


    private void initNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        networkChangeReceiver = new NetworkChangeReceiver(new NetworkChangeReceiver.NetworkChangeListener() {
            @Override
            public void onNetworkConnected() {
                if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() == R.id.noNetworkFragment) {
                    changeMenu(true);
                    navController.popBackStack();
                }
            }

            @Override
            public void onNetworkDisconnected() {
                changeMenu(false);
                navController.navigate(R.id.noNetworkFragment);
            }
        });
    }

    public void changeMenu(boolean isShow) {
        binding.bottomNavigationView.setVisibility(isShow ? View.VISIBLE : View.GONE);

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            NavigationUI.onNavDestinationSelected(item, navController);

            return true;
        });
    }
}