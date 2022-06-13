package com.baec.vocabularyquiz.login;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.baec.vocabularyquiz.repository.user.UserRepositoryTestImpl;
import com.baec.vocabularyquiz.util.ViewModelToastMessage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    LoginViewModel loginViewModel;

    @Before
    public void setup(){
        loginViewModel = new LoginViewModel(new UserRepositoryTestImpl());
    }

    @Test
    public void onUsernameTextInvalid_usernameErrorMessageNotMinusOne() {
        loginViewModel.onUsernameTextChanged("abc");
        assertThat(loginViewModel.getUsernameErrorMessageStringId().getValue()).isGreaterThan(-1);
    }

    @Test
    public void onUsernameTextValid_usernameErrorMessageMinusOne() {
        loginViewModel.onUsernameTextChanged("abc@abc.com");
        assertThat(loginViewModel.getUsernameErrorMessageStringId().getValue()).isEqualTo(-1);
    }

    @Test
    public void onPasswordTextInvalid_passwordErrorMessageNotMinusOne() {
        loginViewModel.onPasswordTextChanged("abcd");
        assertThat(loginViewModel.getPasswordErrorMessageStringId().getValue()).isGreaterThan(-1);
    }

    @Test
    public void onPasswordTextValid_passwordErrorMessageMinusOne() {
        loginViewModel.onPasswordTextChanged("abcdabcd!");
        assertThat(loginViewModel.getPasswordErrorMessageStringId().getValue()).isEqualTo(-1);
    }

    @Test
    public void onInputsInvalid_validationOkayFalse() {
        loginViewModel.onUsernameTextChanged("abcabc.com");
        loginViewModel.onPasswordTextChanged("abcdabcd!");
        assertThat(loginViewModel.getValidationOkay().getValue()).isFalse();
    }

    @Test
    public void onInputsValid_validationOkayTrue() {
        loginViewModel.onUsernameTextChanged("abc@abc.com");
        loginViewModel.onPasswordTextChanged("abcdabcd!");
        assertThat(loginViewModel.getValidationOkay().getValue()).isTrue();
    }

    @Test
    public void onLoginButtonPressedSuccess_toastMessageTypeMessage(){
        loginViewModel.onUsernameTextChanged("test@test.com");
        loginViewModel.onPasswordTextChanged("abcdabcd!");
        loginViewModel.onLoginButtonPressed();
        assertThat(loginViewModel.getToastMessage().getValue().getType()).isEqualTo(ViewModelToastMessage.Type.MESSAGE);
    }

    @Test
    public void onLoginButtonPressedFailed_toastMessageTypeError(){
        loginViewModel.onUsernameTextChanged("abc@abc.com");
        loginViewModel.onPasswordTextChanged("abcdabcd!");
        loginViewModel.onLoginButtonPressed();
        assertThat(loginViewModel.getToastMessage().getValue().getType()).isEqualTo(ViewModelToastMessage.Type.ERROR);
    }
}