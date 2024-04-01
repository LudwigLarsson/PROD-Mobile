package com.example.finalprodproject.feature_user.presentation.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ProfileFragmentBinding;
import com.example.finalprodproject.feature_user.data.models.UserProfile;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;

public class ProfileFragment extends Fragment {
    private ProfileFragmentBinding binding;
    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileFragmentBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        viewModel.getProfile().observe(requireActivity(), userProfile -> {
            if (userProfile != null) {
                binding.userPhone.setText(userProfile.getEmail());
                binding.userName.setText(userProfile.getLogin());
//                binding.profileScores.setText(userProfile.);

            }
        });

        binding.editProfile.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_editProfileDialogFragment);
        });

        return binding.getRoot();
    }
}
