package com.baec.vocabularyquiz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.baec.vocabularyquiz.databinding.FragmentLoginBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    @Inject
    LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        et_username = binding.loginEtUsername;
        et_password = binding.loginEtPassword;
        bt_login = binding.loginBtLogin;

        et_username.addTextChangedListener(getUsernameTextWatcher());
        et_password.addTextChangedListener(getPasswordTextWatcher());

        //region Observers
        loginViewModel.getValidationOkay().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isValidationOkay) {
                bt_login.setEnabled(isValidationOkay);
            }
        });

        loginViewModel.getUsernameErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if(id == -1)
                et_username.setError(null);
            else
                et_username.setError(getString(id));
        });

        loginViewModel.getPasswordErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if(id == -1)
                et_password.setError(null);
            else
                et_password.setError(getString(id));
        });

        loginViewModel.getToastMessage().observe(getViewLifecycleOwner(), viewModelToastMessage -> {
            if (viewModelToastMessage.getType() == ViewModelToastMessage.Type.ERROR)
                Toast.makeText(getContext(), getString(viewModelToastMessage.getMessageStringId()), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(), getString(viewModelToastMessage.getMessageStringId()), Toast.LENGTH_SHORT).show();
        });
        //endregion

        //region OnClickListeners
        bt_login.setOnClickListener(v -> loginViewModel.onLoginButtonPressed());
        //endregion

        return binding.getRoot();
    }

    private TextWatcher getUsernameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.onUsernameTextChanged(s.toString());
            }
        };
    }

    private TextWatcher getPasswordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.onPasswordTextChanged(s.toString());
            }
        };
    }
}