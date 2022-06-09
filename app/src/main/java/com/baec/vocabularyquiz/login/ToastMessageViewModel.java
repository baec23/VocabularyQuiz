package com.baec.vocabularyquiz.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baec.vocabularyquiz.util.ViewModelToastMessage;

public class ToastMessageViewModel extends ViewModel {
    MutableLiveData<ViewModelToastMessage> toastMessage = new MutableLiveData<>();

    public LiveData<ViewModelToastMessage> getToastMessage() {
        return toastMessage;
    }
}
