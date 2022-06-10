package com.baec.vocabularyquiz.repository;

import com.baec.vocabularyquiz.model.QuizWord;
import com.baec.vocabularyquiz.util.Result;

import java.util.List;

public interface QuizWordRepository {
    QuizWord getRandomQuizWord();
    List<String> getAnswers(QuizWord quizWord, int numTotalAnswers);
    void addQuizWord(QuizWord quizWord, RepositoryCallback<Result<String>> callback);
}
