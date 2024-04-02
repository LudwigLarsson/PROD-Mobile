package com.example.finalprodproject.feature_user.presentation.screens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.databinding.ChangeFragmentBinding;
import com.example.finalprodproject.feature_roadmap.presentation.factories.ThemesViewModelFactory;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;
import com.example.finalprodproject.feature_shop.data.models.CourseShopModel;

public class BuyCourseDialogFragment extends DialogFragment {
//    private ChangeFragmentBinding binding;
    private CourseShopModel courseShopModel;

    public BuyCourseDialogFragment(CourseShopModel courseShopModel) {
        this.courseShopModel = courseShopModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ThemesViewModel viewModel = new ViewModelProvider(requireActivity(), new ThemesViewModelFactory(requireActivity().getApplication())).get(ThemesViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(courseShopModel.getTitle()).setMessage("Вы уверены, что хотите купить этот товар за " + courseShopModel.getPrice() + " монет?")
                .setPositiveButton("Подтвердить", (dialog, which) -> {
                    viewModel.buyCourse(courseShopModel.getId());
                })
                .setNegativeButton("Отмена", (dialog, which) -> {

                });

        return builder.create();
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = ChangeFragmentBinding.inflate(inflater, container, false);
//
//        return binding.getRoot();
//    }
}
