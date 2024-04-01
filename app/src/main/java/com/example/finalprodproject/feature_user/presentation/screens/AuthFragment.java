package com.example.finalprodproject.feature_user.presentation.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.AuthFragmentBinding;
import com.example.finalprodproject.feature_user.presentation.factories.UserViewModelFactory;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;
import com.google.android.material.tabs.TabLayout;

public class AuthFragment extends Fragment {
    private AuthFragmentBinding binding;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(inflater, container, false);

        userViewModel = new ViewModelProvider(requireActivity(), new UserViewModelFactory(requireActivity().getApplication())).get(UserViewModel.class);

        userViewModel.checkAuth().observe(requireActivity(), isAuth -> {
            if (isAuth) Navigation.findNavController(requireView()).navigate(R.id.action_authFragment_to_shopFragment);
        });

        binding.nextButton.setOnClickListener(view -> {
            String email = binding.inputPhone.getText().toString();
            String login = binding.inputName.getText().toString();
            String password = binding.inputPassword.getText().toString();
            userViewModel.register(email, login, password);
            Log.d("reg", "done");
        });

        binding.nextButton1.setOnClickListener(view -> {
            String login = binding.inputPhone1.getText().toString();
            String password = binding.inputPassword1.getText().toString();
            userViewModel.login(login, password);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registrationTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.viewSwitcher.showNext();
                binding.loginTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_button));
                binding.registrationTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_button));
                binding.registrationTv.setTextColor(Color.BLACK);
                binding.loginTv.setTextColor(Color.parseColor("#818C99"));
            }
        });

        binding.loginTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.viewSwitcher.showNext();
                binding.loginTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_button));
                binding.registrationTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_button));
                binding.loginTv.setTextColor(Color.BLACK);
                binding.registrationTv.setTextColor(Color.parseColor("#818C99"));
            }
        });

        userViewModel.getLoginLoader().observe(getViewLifecycleOwner(), loaderState -> {
            if (loaderState != null) {
                switch (loaderState) {
                    case LOADING:
                        binding.authLoader.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        binding.authLoader.setVisibility(View.GONE);
                        Navigation.findNavController(requireView()).navigate(R.id.action_authFragment_to_shopFragment);

                        break;
                    case ERROR:
                        binding.authLoader.setVisibility(View.GONE);
                        break;
                    default:
                        binding.authLoader.setVisibility(View.GONE);
                }
            }
        });

        userViewModel.getLoaderCheckAuth().observe(getViewLifecycleOwner(), loaderState -> {
            if (loaderState != null) {
                switch (loaderState) {
                    case LOADING:
                        binding.authLoader.setVisibility(View.VISIBLE);
                        binding.login.setVisibility(View.GONE); // сомнительно, но окэй
                        break;
                    default:
                        binding.authLoader.setVisibility(View.GONE);
                }
            }
        });

        userViewModel.getStatusCode().observe(getViewLifecycleOwner(), statusCode -> {
            switch (statusCode) {
                case 400:
                    binding.authError.setText("Не те символы");
                    break;
                case 401:
                    binding.authError.setText("Не авторизован");
                    break;
                case 404:
                    binding.authError.setText("Не найден");
                    break;
                case 409:
                    binding.authError.setText("Пользователь с такими данными уже существует");
                    break;
                default:
                    binding.authError.setText("");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        userViewModel.updateStatusCode(0);
        userViewModel.updateRegisterLoader(null);
        userViewModel.updateLoginLoader(null);
    }
}