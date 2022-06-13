package com.baec.vocabularyquiz.login;

import static com.baec.vocabularyquiz.util.InputValidator.isPasswordValid;
import static com.baec.vocabularyquiz.util.InputValidator.isUsernameValid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.baec.vocabularyquiz.R;
import com.baec.vocabularyquiz.repository.user.UserRepository;
import com.baec.vocabularyquiz.util.Result;
import com.baec.vocabularyquiz.util.ViewModelToastMessage;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ToastMessageViewModel {
    private UserRepository userRepository;

    private MutableLiveData<Boolean> loggedIn = new MutableLiveData<>(false);
    private MutableLiveData<Integer> usernameErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Integer> passwordErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Boolean> validationOkay = new MutableLiveData<>(false);

    private String username = "";
    private String password = "";

    @Inject
    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onLoginButtonPressed() {
        userRepository.tryLogin(username, password, callbackResult -> {
            if (callbackResult instanceof Result.Success) {
                toastMessage.postValue(new ViewModelToastMessage(ViewModelToastMessage.Type.MESSAGE, R.string.message_loginSuccessful));
                loggedIn.postValue(true);
            } else {
                toastMessage.postValue(new ViewModelToastMessage(ViewModelToastMessage.Type.ERROR, R.string.message_loginFailed));
                loggedIn.postValue(false);
            }
        });
    }

    public void onUsernameTextChanged(String changedText) {
        username = changedText;
        if (username.length() > 0 && !isUsernameValid(username))
            usernameErrorMessageStringId.postValue(R.string.errorMessage_usernameValidation);
        else
            usernameErrorMessageStringId.postValue(-1);
        checkValidationOkay();
    }

    public void onPasswordTextChanged(String changedText) {
        password = changedText;
        if (password.length() > 0 && !isPasswordValid(password))
            passwordErrorMessageStringId.postValue(R.string.errorMessage_passwordValidation);
        else
            passwordErrorMessageStringId.postValue(-1);
        checkValidationOkay();
    }

    private void checkValidationOkay() {
        if (isUsernameValid(username) && isPasswordValid(password)) {
            validationOkay.postValue(true);
        } else {
            validationOkay.postValue(false);
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

    public LiveData<Boolean> isLoggedIn() {
        return loggedIn;
    }
}