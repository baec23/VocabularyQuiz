package com.baec.vocabularyquiz.login;

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
import com.baec.vocabularyquiz.databinding.FragmentRegisterBinding;
import com.baec.vocabularyquiz.util.ViewModelToastMessage;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private FragmentRegisterBinding binding;
    private NavController navController;

    private EditText et_username;
    private EditText et_password;
    private EditText et_password2;
    private Button bt_register;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        et_username = binding.registerEtUsername;
        et_password = binding.registerEtPassword;
        et_password2 = binding.registerEtPassword2;
        bt_register = binding.registerBtRegister;

        et_username.addTextChangedListener(getUsernameTextWatcher());
        et_password.addTextChangedListener(getPasswordTextWatcher());
        et_password2.addTextChangedListener(getPassword2TextWatcher());

        //region Observers
        registerViewModel.getRegistrationStatus().observe(getViewLifecycleOwner(), registrationStatus -> {
            if (registrationStatus.getStatus() == RegistrationStatus.Status.SUCCESS) {
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            } else {
                bt_register.setEnabled(true);
            }
        });

        registerViewModel.getValidationOkay().observe(getViewLifecycleOwner(), isValidationOkay -> bt_register.setEnabled(isValidationOkay));

        registerViewModel.getUsernameErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if (id == -1)
                et_username.setError(null);
            else
                et_username.setError(getString(id));
        });

        registerViewModel.getPasswordErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if (id == -1)
                et_password.setError(null);
            else
                et_password.setError(getString(id));
        });

        registerViewModel.getPassword2ErrorMessageStringId().observe(getViewLifecycleOwner(), id -> {
            if (id == -1)
                et_password2.setError(null);
            else
                et_password2.setError(getString(id));
        });

        registerViewModel.getToastMessage().observe(getViewLifecycleOwner(), viewModelToastMessage -> {
            if (viewModelToastMessage.getType() == ViewModelToastMessage.Type.ERROR)
                Toast.makeText(getContext(), getString(viewModelToastMessage.getMessageStringId()), Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(), getString(viewModelToastMessage.getMessageStringId()), Toast.LENGTH_SHORT).show();
        });
        //endregion

        //region OnClickListeners
        bt_register.setOnClickListener(v -> {
            registerViewModel.onRegisterButtonPressed();
            bt_register.setEnabled(false);
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
                registerViewModel.onUsernameTextChanged(s.toString());
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
                registerViewModel.onPasswordTextChanged(s.toString());
            }
        };
    }

    private TextWatcher getPassword2TextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.onPassword2TextChanged(s.toString());
            }
        };
    }
}