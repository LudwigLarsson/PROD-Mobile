package com.example.finalprodproject.feature_user.presentation.screens;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.finalprodproject.databinding.ChangeFragmentBinding;

public class EditProfileDialogFragment extends DialogFragment {
    private ChangeFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = ChangeFragmentBinding.inflate(inflater, container, false);

         binding.btnDialog.setOnClickListener(v -> {
             String name = binding.inputFirstname.getText().toString();
             String phone = binding.inputLastname.getText().toString();
             String otchestvo = binding.inputOtchestvo.getText().toString();

             dismiss();
         });


         return binding.getRoot();
    }
}
