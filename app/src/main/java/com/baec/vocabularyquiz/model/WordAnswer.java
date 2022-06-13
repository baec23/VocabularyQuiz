package com.baec.vocabularyquiz.model;

import static com.baec.vocabularyquiz.util.EqualHelper.equalHelper;

import androidx.annotation.Nullable;

public class WordAnswer {
    private String answer;
    private AnswerGuessState answerGuessState;

    public WordAnswer(String answer, AnswerGuessState answerGuessState) {
        this.answer = answer;
        this.answerGuessState = answerGuessState;
    }

    public String getAnswer() {
        return answer;
    }

    public AnswerGuessState getAnswerGuessState() {
        return answerGuessState;
    }

    public void setAnswerGuessState(AnswerGuessState answerGuessState) {
        this.answerGuessState = answerGuessState;
    }

    public enum AnswerGuessState {
        NOT_GUESSED,
        INCORRECT,
        CORRECT,
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof WordAnswer))
            return false;
        WordAnswer toCompare = (WordAnswer)obj;
        if(!equalHelper(answer, toCompare.answer))
            return false;
        if(!equalHelper(answerGuessState, toCompare.answerGuessState))
            return false;
        return true;
    }
}
