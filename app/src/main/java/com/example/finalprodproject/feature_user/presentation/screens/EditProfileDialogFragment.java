package com.example.finalprodproject.feature_user.presentation.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalprodproject.databinding.ChangeFragmentBinding;
import com.example.finalprodproject.feature_user.presentation.factories.UserViewModelFactory;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;
import com.example.finalprodproject.utils.enums.LoaderState;

public class EditProfileDialogFragment extends DialogFragment {
    private ChangeFragmentBinding binding;
    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ChangeFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity(), new UserViewModelFactory(requireActivity().getApplication())).get(UserViewModel.class);

         binding.btnDialog.setOnClickListener(v -> {
             String name = binding.inputFirstname.getText().toString();
             String lastname = binding.inputLastname.getText().toString();
             String surname = binding.inputOtchestvo.getText().toString();

             viewModel.updateProfile(name, lastname, surname).observe(requireActivity(), loaderState -> {
                 if (loaderState == LoaderState.ERROR) {
                     binding.updateProfileError.setText("Произошла ошибка");
                     binding.updateProfileError.setVisibility(View.VISIBLE);
                     binding.btnDialog.setEnabled(true);
                 } else if (loaderState == LoaderState.SUCCESS) {
                     binding.updateProfileError.setText("");
                     binding.updateProfileError.setVisibility(View.GONE);

                     binding.inputFirstname.setText("");
                     binding.inputLastname.setText("");
                     binding.inputOtchestvo.setText("");
                     binding.btnDialog.setEnabled(true);

                     dismiss();
                 } else if (loaderState == LoaderState.LOADING) {
                     binding.updateProfileError.setText("");
                     binding.updateProfileError.setVisibility(View.GONE);
                     binding.btnDialog.setEnabled(false);
                 }
             });
         });


         return binding.getRoot();
    }
}
