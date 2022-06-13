package com.baec.vocabularyquiz.main.ui.playquiz;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.baec.vocabularyquiz.model.WordAnswer;
import com.baec.vocabularyquiz.repository.quizword.QuizWordRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayQuizViewModel extends ViewModel {
    private QuizWordRepository quizWordRepository;
    private QuizGameState quizGameState;
    private MutableLiveData<Boolean> answerStatusChanged = new MutableLiveData<>(false);

    //Not Loaded
    //Loaded - Not Answered
    //Loaded - Answered

    @Inject
    public PlayQuizViewModel(QuizWordRepository quizWordRepository) {
        this.quizWordRepository = quizWordRepository;
        quizGameState = new QuizGameState();
        Log.d("DEBUG", "!!!---!!!PlayQuizViewModel constructor called!!!---!!!");
    }

    public QuizGameState getQuizGameState(){
        return quizGameState;
    }

    public void onAnswerClick(WordAnswer clicked){
        if(clicked.getAnswerGuessState() != WordAnswer.AnswerGuessState.NOT_GUESSED)
            return;
        String correctAnswer = quizGameState.getCurrQuizWord().getAnswer();
        if(clicked.getAnswer().equals(correctAnswer)) {
            clicked.setAnswerGuessState(WordAnswer.AnswerGuessState.CORRECT);
            quizGameState.setLoadingState(QuizGameState.LoadingState.ANSWERED);
        }
        else{
            clicked.setAnswerGuessState(WordAnswer.AnswerGuessState.INCORRECT);
        }
        answerStatusChanged.postValue(true);
    }

    public void loadWord(){
        loadQuizWord();
        loadRandomAnswers();
        quizGameState.setLoadingState(QuizGameState.LoadingState.LOADED);
    }

    private void loadQuizWord(){
        quizGameState.setCurrQuizWord(quizWordRepository.getRandomQuizWord());
    }

    private void loadRandomAnswers(){
        quizGameState.setCurrWordAnswers(quizWordRepository.getAnswers(quizGameState.getCurrQuizWord(), 4));
    }

    public LiveData<Boolean> isAnswerStatusChanged(){
        return answerStatusChanged;
    }
}