package com.example.finalprodproject.feature_shop.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.databinding.ShopFragmentBinding;
import com.example.finalprodproject.feature_roadmap.presentation.factories.ThemesViewModelFactory;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;
import com.example.finalprodproject.feature_shop.presentation.adapters.ShopCategoryAdapter;


public class ShopFragment extends Fragment {
    private ShopFragmentBinding binding;
    private ThemesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ShopFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, new ThemesViewModelFactory(requireActivity().getApplication())).get(ThemesViewModel.class);

        viewModel.getCategories().observe(requireActivity(), categories -> {
            if (categories != null) {
                ShopCategoryAdapter adapter = new ShopCategoryAdapter(categories);
                adapter.setOnItemClickListener(id -> {

                });
                binding.categoriesList.setAdapter(adapter);
            }
        });


        return binding.getRoot();
    }
}
