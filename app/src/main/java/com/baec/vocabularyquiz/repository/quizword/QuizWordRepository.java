package com.baec.vocabularyquiz.repository.quizword;

import androidx.lifecycle.LiveData;

import com.baec.vocabularyquiz.model.WordAnswer;
import com.baec.vocabularyquiz.model.QuizWord;
import com.baec.vocabularyquiz.repository.RepositoryCallback;
import com.baec.vocabularyquiz.util.Result;

import java.util.List;

public interface QuizWordRepository {
    QuizWord getRandomQuizWord();
    List<WordAnswer> getAnswers(QuizWord quizWord, int numTotalAnswers);
    void addQuizWord(QuizWord quizWord, RepositoryCallback<Result<String>> callback);
    void init();
    LiveData<Boolean> isLoaded();
}
