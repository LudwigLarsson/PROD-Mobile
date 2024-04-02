package com.example.finalprodproject.feature_shop.presentation.fragments;

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
import androidx.navigation.Navigation;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.ChangeFragmentBinding;
import com.example.finalprodproject.feature_roadmap.presentation.factories.ThemesViewModelFactory;
import com.example.finalprodproject.feature_roadmap.presentation.viewmodels.ThemesViewModel;
import com.example.finalprodproject.feature_shop.data.models.CourseShopModel;

public class BuyCourseDialogFragment extends DialogFragment {
//    private ChangeFragmentBinding binding;
    private String title = "";
    private int price = 0;
    private int id = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString("title");
            price = args.getInt("price");
            id = args.getInt("id");
        }

        ThemesViewModel viewModel = new ViewModelProvider(requireActivity(), new ThemesViewModelFactory(requireActivity().getApplication())).get(ThemesViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title).setMessage("Вы уверены, что хотите купить этот товар за " + price + " монет?")
                .setPositiveButton("Подтвердить", (dialog, which) -> {
                    viewModel.buyCourse(id);
                    Navigation.findNavController(requireView()).navigate(R.id.roadmapFragment);
                    dismiss();
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
