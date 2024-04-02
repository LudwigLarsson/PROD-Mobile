package com.example.finalprodproject.feature_user.presentation.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.AuthFragmentBinding;
import com.example.finalprodproject.feature_user.presentation.factories.UserViewModelFactory;
import com.example.finalprodproject.feature_user.presentation.viewmodels.UserViewModel;
import com.example.finalprodproject.utils.KeyboardUtils;

public class AuthFragment extends Fragment {
    private AuthFragmentBinding binding;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(inflater, container, false);

        userViewModel = new ViewModelProvider(requireActivity(), new UserViewModelFactory(requireActivity().getApplication())).get(UserViewModel.class);


        binding.successLayout.nextButton.setOnClickListener(view -> {
            String email = binding.successLayout.inputPhone.getText().toString();
            String login = binding.successLayout.inputName.getText().toString();
            String password = binding.successLayout.inputPassword.getText().toString();
            userViewModel.register(email, login, password);
            Log.d("reg", "done");
            KeyboardUtils.hideKeyboard(this);
        });

        binding.successLayout.nextButton1.setOnClickListener(view -> {
            String login = binding.successLayout.inputPhone1.getText().toString();
            String password = binding.successLayout.inputPassword1.getText().toString();
            userViewModel.login(login, password);
            KeyboardUtils.hideKeyboard(this);
        });


        binding.successLayout.registrationTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.successLayout.viewSwitcher.setDisplayedChild(0);
                binding.successLayout.loginTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_button));
                binding.successLayout.registrationTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_button));
                binding.successLayout.registrationTv.setTextColor(Color.BLACK);
                binding.successLayout.loginTv.setTextColor(Color.parseColor("#818C99"));
            }
        });

        binding.successLayout.loginTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.successLayout.viewSwitcher.setDisplayedChild(1);
                binding.successLayout.loginTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_button));
                binding.successLayout.registrationTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_button));
                binding.successLayout.loginTv.setTextColor(Color.BLACK);
                binding.successLayout.registrationTv.setTextColor(Color.parseColor("#818C99"));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userViewModel.checkAuth().observe(getViewLifecycleOwner(), isAuth -> {
            if (isAuth)
                Navigation.findNavController(requireView()).navigate(R.id.action_authFragment_to_shopFragment);
        });

        userViewModel.getRegisterLoader().observe(getViewLifecycleOwner(), loaderState -> {
            if (loaderState != null) {
                switch (loaderState) {
                    case LOADING:
                        binding.loadingLayout.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.loadingLayout.setVisibility(View.GONE);
                        binding.successLayout.loginTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_button));
                        binding.successLayout.registrationTv.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_button));
                        binding.successLayout.loginTv.setTextColor(Color.BLACK);
                        binding.successLayout.registrationTv.setTextColor(Color.parseColor("#818C99"));
                        binding.successLayout.viewSwitcher.setDisplayedChild(1);
                        break;
                    case ERROR:
                        binding.loadingLayout.setVisibility(View.GONE);
                        break;
                    default:
                        binding.loadingLayout.setVisibility(View.GONE);
                }

                userViewModel.getLoginLoader().observe(getViewLifecycleOwner(), loaderState1 -> {
                    if (loaderState1 != null) {
                        switch (loaderState1) {
                            case LOADING:
                                binding.loadingLayout.setVisibility(View.VISIBLE);
                                break;

                            case SUCCESS:
                                binding.loadingLayout.setVisibility(View.GONE);
                                Navigation.findNavController(requireView()).navigate(R.id.shopFragment);
                                Log.d("navigation", "nav");

                                break;
                            case ERROR:
                                binding.loadingLayout.setVisibility(View.VISIBLE);
                                break;
                            default:
                                binding.loadingLayout.setVisibility(View.GONE);
                        }
                    }
                });

                userViewModel.getLoaderCheckAuth().observe(getViewLifecycleOwner(), loaderState2 -> {
                    if (loaderState2 != null) {
                        switch (loaderState2) {
                            case LOADING:
                                binding.loadingLayout.setVisibility(View.VISIBLE);
                                binding.successAuth.setVisibility(View.GONE);
                                break;
                            default:
                                binding.successAuth.setVisibility(View.VISIBLE);
                        }
                    }
                });

                userViewModel.getStatusCode().observe(getViewLifecycleOwner(), statusCode -> {
                    switch (statusCode) {
                        case 400:
                            binding.successLayout.authErrorIcon.setVisibility(View.VISIBLE);
                            binding.successLayout.text2.setVisibility(View.VISIBLE);
                            binding.successLayout.authError.setText("Неправильный формат ввода данных");
                            break;
                        case 401:
                            binding.successLayout.authErrorIcon.setVisibility(View.VISIBLE);
                            binding.successLayout.text2.setVisibility(View.GONE);
                            binding.successLayout.authError.setText("Не авторизован");
                            break;
                        case 404:
                            binding.successLayout.authErrorIcon.setVisibility(View.VISIBLE);
                            binding.successLayout.text2.setVisibility(View.GONE);
                            binding.successLayout.authError.setText("Не найден");
                            break;
                        case 409:
                            binding.successLayout.authErrorIcon.setVisibility(View.VISIBLE);
                            binding.successLayout.text2.setVisibility(View.GONE);
                            binding.successLayout.authError.setText("Пользователь с такими данными уже существует");
                            break;
                        default:
                            binding.successLayout.authErrorIcon.setVisibility(View.GONE);
                            binding.successLayout.text2.setVisibility(View.GONE);
                            binding.successLayout.authError.setText("");
                    }
                });
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