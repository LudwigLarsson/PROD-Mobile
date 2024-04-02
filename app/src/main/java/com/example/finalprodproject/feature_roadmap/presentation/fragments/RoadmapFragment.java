package com.example.finalprodproject.feature_roadmap.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.RoadmapLayoutBinding;
import com.example.finalprodproject.feature_main.data.RoadmapItem;
import com.example.finalprodproject.feature_roadmap.data.models.UnderTheme;
import com.example.finalprodproject.feature_roadmap.presentation.adapters.GraphAdapter;
import com.example.finalprodproject.feature_roadmap.presentation.factories.ThemesViewModelFactory;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;

import java.util.ArrayList;

public class RoadmapFragment extends Fragment {
    private RoadmapLayoutBinding binding;
    private ThemesViewModel viewModel;
    private GraphAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavController navController = NavHostFragment.findNavController(this);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navController.navigate(RoadmapFragmentDirections.actionRoadmapFragmentToStudyFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RoadmapLayoutBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity(), new ThemesViewModelFactory(requireActivity().getApplication())).get(ThemesViewModel.class);

        binding.roadmapBack.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoadmapFragmentArgs args = RoadmapFragmentArgs.fromBundle(getArguments());
        int receivedArg = args.getId();
        viewModel.loadThemeData(receivedArg).observe(getViewLifecycleOwner(), themeDTO -> {
            if (themeDTO != null) {
                binding.roadmapTitle.setText(themeDTO.getTitle());
            }
        });

        viewModel.getUnderThemes().observe(getViewLifecycleOwner(), themes -> {
            if (themes != null) {
                ArrayList<RoadmapItem> roadmapItems = new ArrayList<>();
                for (UnderTheme underTheme: themes) {
                    roadmapItems.add(new RoadmapItem(underTheme.getTitle(), underTheme.getImage(), underTheme.getPoints(), underTheme.getId()));
                }
                adapter = new GraphAdapter(roadmapItems);
                adapter.setOnItemClickListener(id -> {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    Navigation.findNavController(requireView()).navigate(R.id.action_roadmapFragment_to_deepEduFragment, bundle);
                });
                binding.graphList.setAdapter(adapter);
                if (!roadmapItems.isEmpty()) binding.graphList.post(() -> binding.graphList.smoothScrollToPosition(roadmapItems.size() - 1));
            }
        });

        viewModel.getPercent().observe(getViewLifecycleOwner(), percent -> {
            if (percent != null && percent != 0) {
                if (percent * 100 <= 25) {
                    binding.progressBar.setProgressTintList(ContextCompat.getColorStateList(requireContext(), R.color.color_stroke_accent_themed));
//                    binding.progressText.setText("3");
                } else if (percent * 100 <= 75) {
                    binding.progressBar.setProgressTintList(ContextCompat.getColorStateList(requireContext(), R.color.color_yellow));
//                    binding.progressText.setText("4");
                } else if (percent * 100 <= 95) {
                    binding.progressBar.setProgressTintList(ContextCompat.getColorStateList(requireContext(), R.color.color_stroke_negative));
//                    binding.progressText.setText("5");
                }
                binding.progressBar.setProgress((int) (percent * 100));
            }
        });

        binding.progressBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.progressBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int screenWidth = getResources().getDisplayMetrics().widthPixels;

                float translationX = -screenWidth * 0.65f;

                binding.progressBar.setTranslationX(translationX);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.progressBar.getLayoutParams();
        params.width = screenHeight - 400;
        params.height = 30;

        binding.progressBar.setLayoutParams(params);
    }
}
