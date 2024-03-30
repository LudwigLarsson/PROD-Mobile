package com.example.finalprodproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initNavigation();
    }

    private void initNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_main_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            NavigationUI.onNavDestinationSelected(item, navController);

            return true;
        });

        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navController.getCurrentDestination() != null) {
                if (navController.getCurrentDestination().getId() == R.id.authFragment) {
                    binding.bottomNavigationView.setVisibility(View.GONE);
                } else {
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}