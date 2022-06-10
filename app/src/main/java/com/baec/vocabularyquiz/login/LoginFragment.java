package com.baec.vocabularyquiz.login;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.baec.vocabularyquiz.R;
import com.baec.vocabularyquiz.databinding.FragmentLoginBinding;
import com.baec.vocabularyquiz.main.MainActivity;
import com.baec.vocabularyquiz.util.ViewModelToastMessage;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;
    private NavController navController;
    private FragmentLoginBinding binding;

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private Button bt_register;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        et_username = binding.loginEtUsername;
        et_password = binding.loginEtPassword;
        bt_login = binding.loginBtLogin;
        bt_register = binding.loginBtRegister;

        et_username.addTextChangedListener(getUsernameTextWatcher());
        et_password.addTextChangedListener(getPasswordTextWatcher());

        //region Observers
        loginViewModel.getValidationOkay().observe(getViewLifecycleOwner(), isValidationOkay -> bt_login.setEnabled(isValidationOkay));

        loginViewModel.getUsernameErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if (id == -1)
                et_username.setError(null);
            else
                et_username.setError(getString(id));
        });

        loginViewModel.getPasswordErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if (id == -1)
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
        bt_register.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        });
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