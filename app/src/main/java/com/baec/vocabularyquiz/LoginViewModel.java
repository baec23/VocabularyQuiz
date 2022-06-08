package com.baec.vocabularyquiz;

import static com.baec.vocabularyquiz.util.InputValidator.isPasswordValid;
import static com.baec.vocabularyquiz.util.InputValidator.isUsernameValid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;

    private MutableLiveData<Integer> usernameErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Integer> passwordErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Boolean> validationOkay = new MutableLiveData<>(false);
    private MutableLiveData<ViewModelToastMessage> toastMessage = new MutableLiveData<>();

    private String username = "";
    private String password = "";

    @Inject
    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onLoginButtonPressed() {
        Result<User> result = userRepository.tryLogin(username, password);
        if (result instanceof Result.Success)
            toastMessage.setValue(new ViewModelToastMessage(ViewModelToastMessage.Type.MESSAGE, R.string.message_loginSuccessful));
        else
            toastMessage.setValue(new ViewModelToastMessage(ViewModelToastMessage.Type.ERROR, R.string.message_loginFailed));
    }

    public void onUsernameTextChanged(String changedText) {
        username = changedText;
        if (username.length() > 0 && !isUsernameValid(username))
            usernameErrorMessageStringId.setValue(R.string.errorMessage_usernameValidation);
        else
            usernameErrorMessageStringId.setValue(-1);
        checkValidationOkay();
    }

    public void onPasswordTextChanged(String changedText) {
        password = changedText;
        if (password.length() > 0 && !isPasswordValid(password))
            passwordErrorMessageStringId.setValue(R.string.errorMessage_passwordValidation);
        else
            passwordErrorMessageStringId.setValue(-1);
        checkValidationOkay();
    }

    private void checkValidationOkay() {
        if (isUsernameValid(username) && isPasswordValid(password)) {
            validationOkay.setValue(true);
        } else {
            validationOkay.setValue(false);
        }
    }

    public LiveData<Integer> getUsernameErrorMessageStringId() {
        return usernameErrorMessageStringId;
    }

    public LiveData<Integer> getPasswordErrorMessageStringId() {
        return passwordErrorMessageStringId;
    }

    public LiveData<Boolean> getValidationOkay() {
        return validationOkay;
    }

    public LiveData<ViewModelToastMessage> getToastMessage() {
        return toastMessage;
    }
}