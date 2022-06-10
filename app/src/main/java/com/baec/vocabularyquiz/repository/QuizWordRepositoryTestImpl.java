package com.baec.vocabularyquiz.repository;

import com.baec.vocabularyquiz.model.QuizWord;
import com.baec.vocabularyquiz.util.Result;

import java.util.ArrayList;
import java.util.List;

public class QuizWordRepositoryTestImpl implements QuizWordRepository {
    private List<QuizWord> quizWords = new ArrayList<>();
    private List<String> potentialAnswers = new ArrayList<>();

    @Override
    public QuizWord getRandomQuizWord() {
        return null;
    }

    @Override
    public List<String> getAnswers(QuizWord quizWord, int numTotalAnswers) {
        return null;
    }

    @Override
    public void addQuizWord(QuizWord quizWord, RepositoryCallback<Result<String>> callback) {

    }

    public void init() {
       quizWords.add(new QuizWord("testWord1", "testAnswer1"));
       quizWords.add(new QuizWord("testWord2", "testAnswer2"));
       quizWords.add(new QuizWord("testWord3", "testAnswer3"));
       quizWords.add(new QuizWord("testWord4", "testAnswer4"));
       quizWords.add(new QuizWord("testWord5", "testAnswer5"));
       potentialAnswers.add("testAnswer1");
       potentialAnswers.add("testAnswer2");
       potentialAnswers.add("testAnswer3");
       potentialAnswers.add("testAnswer4");
       potentialAnswers.add("testAnswer5");
    }
}
