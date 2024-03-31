package com.example.finalprodproject.feature_user.presentation.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.AuthFragmentBinding;
import com.example.finalprodproject.feature_user.presentation.factories.UserViewModelFactory;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;

public class AuthFragment extends Fragment {
    private AuthFragmentBinding binding;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(inflater, container, false);

        userViewModel = new ViewModelProvider(requireActivity(), new UserViewModelFactory(requireActivity().getApplication())).get(UserViewModel.class);

        userViewModel.checkAuth().observe(requireActivity(), isAuth -> {
            if (isAuth) Navigation.findNavController(requireView()).navigate(R.id.action_authFragment_to_homeFragment);
        });

        binding.authLoginButton.setOnClickListener(view -> {
            String login = binding.authLoginLogin.getText().toString();
            String password = binding.authLoginPassword.getText().toString();
            userViewModel.login(login, password);
        });

        binding.authRegisterButton.setOnClickListener(view -> {
            String email = binding.authRegisterEmail.getText().toString();
            String login = binding.authRegisterName.getText().toString();
            String password = binding.authRegisterPassword.getText().toString();
            userViewModel.register(email, login, password);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userViewModel.getRegisterLoader().observe(getViewLifecycleOwner(), loaderState -> {
            if (loaderState != null) {
                switch (loaderState) {
                    case LOADING:
                        binding.authLoader.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.authLoader.setVisibility(View.GONE);
                        break;
                    case ERROR:
                        binding.authLoader.setVisibility(View.GONE);
                        break;
                    default:
                        binding.authLoader.setVisibility(View.GONE);
                }
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
                        Navigation.findNavController(requireView()).navigate(R.id.action_authFragment_to_homeFragment);

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
                        binding.authLayout.setVisibility(View.GONE);
                        break;
                    default:
                        binding.authLoader.setVisibility(View.GONE);
                        binding.authLayout.setVisibility(View.VISIBLE);
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