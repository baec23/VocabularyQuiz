package com.baec.vocabularyquiz.login;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.baec.vocabularyquiz.repository.user.UserRepositoryTestImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegisterViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    RegisterViewModel registerViewModel;

    @Before
    public void setup() {
        registerViewModel = new RegisterViewModel(new UserRepositoryTestImpl());
    }

    @Test
    public void onUsernameTextInvalid_usernameErrorMessageNotMinusOne() {
        registerViewModel.onUsernameTextChanged("abc");
        assertThat(registerViewModel.getUsernameErrorMessageStringId().getValue()).isGreaterThan(-1);
    }

    @Test
    public void onUsernameTextValid_usernameErrorMessageMinusOne() {
        registerViewModel.onUsernameTextChanged("abc@abc.com");
        assertThat(registerViewModel.getUsernameErrorMessageStringId().getValue()).isEqualTo(-1);
    }

    @Test
    public void onPasswordTextInvalid_passwordErrorMessageNotMinusOne() {
        registerViewModel.onPasswordTextChanged("abcd");
        assertThat(registerViewModel.getPasswordErrorMessageStringId().getValue()).isGreaterThan(-1);
    }

    @Test
    public void onPasswordTextValid_passwordErrorMessageMinusOne() {
        registerViewModel.onPasswordTextChanged("abcdabcd!");
        assertThat(registerViewModel.getPasswordErrorMessageStringId().getValue()).isEqualTo(-1);
    }

    @Test
    public void onPassword2NotEqualToPassword_password2ErrorMessageNotMinusOne() {
        registerViewModel.onPasswordTextChanged("abcdabcd!");
        registerViewModel.onPassword2TextChanged("abcdabcd");
        assertThat(registerViewModel.getPassword2ErrorMessageStringId().getValue()).isGreaterThan(-1);
    }

    @Test
    public void onInputsInvalid_validationOkayFalse() {
        registerViewModel.onUsernameTextChanged("abc@abc.com");
        registerViewModel.onPasswordTextChanged("abcdabcd!");
        registerViewModel.onPassword2TextChanged("abcdabcd");
        assertThat(registerViewModel.getValidationOkay().getValue()).isFalse();
    }

    @Test
    public void onInputsValid_validationOkayTrue() {
        registerViewModel.onUsernameTextChanged("abc@abc.com");
        registerViewModel.onPasswordTextChanged("abcdabcd!");
        registerViewModel.onPassword2TextChanged("abcdabcd!");
        assertThat(registerViewModel.getValidationOkay().getValue()).isTrue();
    }

    @Test
    public void onRegisterButtonPressedSuccess_registrationStatusSuccess() {
        registerViewModel.onUsernameTextChanged("test@test.com");
        registerViewModel.onPasswordTextChanged("abcdabcd");
        registerViewModel.onPassword2TextChanged("abcdabcd");
        registerViewModel.onRegisterButtonPressed();
        assertThat(registerViewModel.getRegistrationStatus().getValue().getStatus()).isEqualTo(RegistrationStatus.Status.SUCCESS);
    }

    @Test
    public void onRegisterButtonPressedFailed_registrationStatusError() {
        registerViewModel.onUsernameTextChanged("test1@test.com");
        registerViewModel.onPasswordTextChanged("abcdabcd");
        registerViewModel.onPassword2TextChanged("abcdabcd");
        registerViewModel.onRegisterButtonPressed();
        assertThat(registerViewModel.getRegistrationStatus().getValue().getStatus()).isEqualTo(RegistrationStatus.Status.ERROR);
    }


}