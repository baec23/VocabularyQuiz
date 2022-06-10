package com.baec.vocabularyquiz.main.ui.addword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baec.vocabularyquiz.model.QuizWord;
import com.baec.vocabularyquiz.repository.QuizWordRepository;
import com.baec.vocabularyquiz.repository.RepositoryCallback;
import com.baec.vocabularyquiz.util.Result;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddWordViewModel extends ViewModel {

    private QuizWordRepository quizWordRepository;

    private MutableLiveData<Boolean> validationOkay = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> wordAdded = new MutableLiveData<>(false);

    private String word = "";
    private String answer = "";

    @Inject
    public AddWordViewModel(QuizWordRepository quizWordRepository) {
        this.quizWordRepository = quizWordRepository;
    }

    public void onWordTextChanged(String changedText){
        word = changedText;
        checkValidationOkay();
    }

    public void onAnswerTextChanged(String changedText){
        answer = changedText;
        checkValidationOkay();
    }

    public void onAddWordClicked(){
        quizWordRepository.addQuizWord(new QuizWord(word, answer), result -> {
            if(result instanceof Result.Success)
                wordAdded.postValue(true);
        });
    }

    private void checkValidationOkay(){
        if(word.length() > 0 && answer.length() > 0)
            validationOkay.postValue(true);
        else
            validationOkay.postValue(false);
    }

    public LiveData<Boolean> isValidationOkay(){
        return validationOkay;
    }

    public LiveData<Boolean> isWordAdded(){
        return wordAdded;
    }
}