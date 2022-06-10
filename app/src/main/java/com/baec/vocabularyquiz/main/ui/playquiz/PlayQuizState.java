package com.baec.vocabularyquiz.main.ui.playquiz;

import com.baec.vocabularyquiz.model.QuizWord;

import java.util.List;

public class PlayQuizState {
    private State state;
    private QuizWord currWord;
    private List<String> currWordAnswers;

    public enum State{
        NOT_LOADED,
        LOADED,
        ANSWERED,
    }
}
