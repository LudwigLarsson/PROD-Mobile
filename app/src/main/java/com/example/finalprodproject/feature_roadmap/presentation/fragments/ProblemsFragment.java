package com.example.finalprodproject.feature_roadmap.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.finalprodproject.databinding.ProblemsFragmentBinding;

public class ProblemsFragment extends Fragment {
    private ProblemsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProblemsFragmentBinding.inflate(getLayoutInflater(), container, false);

        binding.arrowBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());

        return binding.getRoot();
    }
}
