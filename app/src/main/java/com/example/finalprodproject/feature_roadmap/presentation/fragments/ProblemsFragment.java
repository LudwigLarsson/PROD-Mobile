package com.example.finalprodproject.feature_roadmap.presentation.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ProblemsFragmentBinding;
import com.example.finalprodproject.feature_roadmap.presentation.factories.ThemesViewModelFactory;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;

public class ProblemsFragment extends Fragment {
    private ProblemsFragmentBinding binding;
    private int underThemeID;
    private ThemesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProblemsFragmentBinding.inflate(getLayoutInflater(), container, false);
        viewModel = new ViewModelProvider(requireActivity(), new ThemesViewModelFactory(requireActivity().getApplication())).get(ThemesViewModel.class);

        Bundle args = getArguments();
        if (args != null) {
            underThemeID = args.getInt("id");

            viewModel.getTasksList(underThemeID).observe(requireActivity(), tasksList -> {
                if (tasksList != null) {

                }
            });
        }

        binding.arrowBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
        binding.nextButton.setOnClickListener(v -> {
            LayoutInflater inflater1 = getLayoutInflater();
            View layout = inflater1.inflate(R.layout.saved_toast, null);
            Toast toast = new Toast(getContext());
            toast.setGravity(Gravity.TOP, 0, 100);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        });


//        binding.roadmap.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_problemsFragment_to_roadmapFragment));

        return binding.getRoot();
    }
}
