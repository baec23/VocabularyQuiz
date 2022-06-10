package com.baec.vocabularyquiz.login;

import static com.baec.vocabularyquiz.util.InputValidator.isPasswordValid;
import static com.baec.vocabularyquiz.util.InputValidator.isUsernameValid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.baec.vocabularyquiz.R;
import com.baec.vocabularyquiz.repository.UserRepository;
import com.baec.vocabularyquiz.util.Result;
import com.baec.vocabularyquiz.util.ViewModelToastMessage;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends ToastMessageViewModel {
    private UserRepository userRepository;

    private MutableLiveData<RegistrationStatus> registrationStatus = new MutableLiveData<>();
    private MutableLiveData<Integer> usernameErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Integer> passwordErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Integer> password2ErrorMessageStringId = new MutableLiveData<>();
    private MutableLiveData<Boolean> validationOkay = new MutableLiveData<>(false);

    private String username = "";
    private String password = "";
    private String password2 = "";

    @Inject
    public RegisterViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onRegisterButtonPressed() {
        userRepository.tryRegister(username, password, resultCallback -> {
            if (resultCallback instanceof Result.Success) {
                registrationStatus.setValue(new RegistrationStatus(RegistrationStatus.Status.SUCCESS, R.string.message_registrationSuccessful));
                toastMessage.setValue(new ViewModelToastMessage(ViewModelToastMessage.Type.MESSAGE, R.string.message_registrationSuccessful));
            } else {
                registrationStatus.setValue(new RegistrationStatus(RegistrationStatus.Status.ERROR, R.string.errorMessage_registration));
                toastMessage.setValue(new ViewModelToastMessage(ViewModelToastMessage.Type.ERROR, R.string.errorMessage_registration));
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

    public void onPassword2TextChanged(String changedText) {
        password2 = changedText;
        if (password2.length() > 0 && !password.equals(password2))
            password2ErrorMessageStringId.postValue(R.string.errorMessage_password2Validation);
        else
            password2ErrorMessageStringId.postValue(-1);
        checkValidationOkay();
    }

    private void checkValidationOkay() {
        if (isUsernameValid(username) && isPasswordValid(password) && password.equals(password2)) {
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

    public LiveData<Integer> getPassword2ErrorMessageStringId() {
        return password2ErrorMessageStringId;
    }

    public LiveData<Boolean> getValidationOkay() {
        return validationOkay;
    }

    public LiveData<RegistrationStatus> getRegistrationStatus() {
        return registrationStatus;
    }
}