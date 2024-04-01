package com.example.finalprodproject.feature_roadmap.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.databinding.ThemeDetailsBinding;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;

public class DeepEduFragment extends Fragment {
    private ThemeDetailsBinding binding;
    private ThemesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ThemeDetailsBinding.inflate(getLayoutInflater(), container, false);

        Bundle args = getArguments();
        if (args != null) {
            viewModel = new ViewModelProvider(this).get(ThemesViewModel.class);

            viewModel.loadThemeData(args.getString("id")).observe(requireActivity(), themeDTO -> {
                if (themeDTO != null) {
                    binding.themeTitle.setText(themeDTO.getCategory());
                    binding.themeName.setText(themeDTO.getTitle());
                }
            });

            binding.webView.getSettings().setJavaScriptEnabled(true);
            String link = "qaFOd_Ho6vI?si=NTCgxaoeAga652BA";
            String url = "https://www.youtube.com/embed/" + link;

            binding.webView.loadUrl(url);


        }

        binding.tv1.setOnClickListener(v -> setMark(1));
        binding.tv1.setOnClickListener(v -> setMark(2));
        binding.tv1.setOnClickListener(v -> setMark(3));
        binding.tv1.setOnClickListener(v -> setMark(4));
        binding.tv1.setOnClickListener(v -> setMark(5));

        return binding.getRoot();
    }

    private void setMark(int mark) {
        viewModel.setMark(mark);
    }
}
