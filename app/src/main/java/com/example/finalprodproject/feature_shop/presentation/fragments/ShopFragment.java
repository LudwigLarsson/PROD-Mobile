package com.example.finalprodproject.feature_shop.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ShopFragmentBinding;
import com.example.finalprodproject.feature_roadmap.presentation.factories.ThemesViewModelFactory;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;
import com.example.finalprodproject.feature_shop.presentation.adapters.ShopCategoryAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;


public class ShopFragment extends Fragment {
    private ShopFragmentBinding binding;
    private ThemesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ShopFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, new ThemesViewModelFactory(requireActivity().getApplication())).get(ThemesViewModel.class);

//        viewModel.getCategories().observe(requireActivity(), categories -> {
//            if (categories != null) {
//
//                for (String category: categories) {
//                    Chip chip = new Chip(requireContext());
//                    chip.setText(category);
//                    chip.setCheckable(true);
//                    chip.setChipBackgroundColorResource(R.color.chip);
//                    chip.setTextColor(getResources().getColor(R.color.chip_text_color));
//
//                    // Обработка события клика на Chip
//                    chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                        if (isChecked) {
//                            // Chip выбран
//                        } else {
//                            // Chip не выбран
//                        }
//                    });
//
//                }

//                ShopCategoryAdapter adapter = new ShopCategoryAdapter(categories);
//                adapter.setOnItemClickListener(category -> {
//
//                });
//                binding.categoriesList.setAdapter(adapter);

//                ChipGroup chipGroup = findViewById(R.id.chipGroup);
//
//                chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(ChipGroup chipGroup, int i) {
//
//                        Chip chip = chipGroup.findViewById(i);
//                        if (chip != null)
//                            Toast.makeText(getApplicationContext(), "Chip is " + chip.getChipText(), Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });

//                binding.categoryList.addView()
//
//            }
//        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getCategories().observe(requireActivity(), categories -> {
            if (categories != null) {

                for (String category: categories) {
                    Chip chip = new Chip(requireContext());
                    chip.setText(category);
                    chip.setCheckable(true);
                    chip.setChipBackgroundColorResource(R.color.white);
                    chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
//                    chip.setChipDrawable(ChipDrawable.createFromResource(requireContext(), R.xml.chip_wrapper));

                    chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked) {
                            chip.setChipBackgroundColorResource(R.color.black);
                            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
                        } else {
                            chip.setChipBackgroundColorResource(R.color.white);
                            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                        }
                    });

                    binding.categoryList.addView(chip);
                }
            }
        });
    }
}
