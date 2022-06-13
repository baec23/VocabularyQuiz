package com.baec.vocabularyquiz.main.ui.playquiz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.baec.vocabularyquiz.model.QuizWord;
import com.baec.vocabularyquiz.model.WordAnswer;

import java.util.List;

public class QuizGameState {

    private MutableLiveData<LoadingState> loadingState = new MutableLiveData<>();
    private QuizWord currQuizWord;
    private List<WordAnswer> currWordAnswers;

    public QuizGameState() {
        loadingState.setValue(LoadingState.NOT_LOADED);
    }

    public LiveData<LoadingState> getLoadingState() {
        return loadingState;
    }

    public void setLoadingState(LoadingState newLoadingState) {
        this.loadingState.postValue(newLoadingState);
    }

    public QuizWord getCurrQuizWord() {
        return currQuizWord;
    }

    public void setCurrQuizWord(QuizWord newQuizWord) {
        this.currQuizWord = newQuizWord;
    }

    public List<WordAnswer> getCurrWordAnswers() {
        return currWordAnswers;
    }

    public void setCurrWordAnswers(List<WordAnswer> newWordAnswers) {
        this.currWordAnswers = newWordAnswers;
    }

    public enum LoadingState {
        NOT_LOADED,
        LOADED,
        ANSWERED,
    }
}