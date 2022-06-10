package com.baec.vocabularyquiz.main.ui.playquiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baec.vocabularyquiz.repository.QuizWordRepository;

import java.util.List;

import javax.inject.Inject;

public class PlayQuizViewModel extends ViewModel {
    private QuizWordRepository quizWordRepository;

    private MutableLiveData<PlayQuizState> playQuizState = new MutableLiveData<PlayQuizState>();

    private String currWord;
    private List<String> currWordAnswers;

    //Not Loaded
    //Loaded - Not Answered
    //Loaded - Answered

    @Inject
    public PlayQuizViewModel(QuizWordRepository quizWordRepository) {
        this.quizWordRepository = quizWordRepository;
    }
}