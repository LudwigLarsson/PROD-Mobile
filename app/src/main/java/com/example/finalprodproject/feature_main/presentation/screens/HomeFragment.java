package com.example.finalprodproject.feature_main.presentation.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.HomeFragmentBinding;
import com.example.finalprodproject.feature_user.domain.helpers.UserStorageHandler;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);

        // перенести на другой экран
        binding.authLogout.setOnClickListener(v -> {
            new UserStorageHandler(requireContext()).logout();
            Navigation.findNavController(requireView()).navigate(com.example.finalprodproject.R.id.action_homeFragment_to_authFragment);
        });

        return binding.getRoot();
    }
}
