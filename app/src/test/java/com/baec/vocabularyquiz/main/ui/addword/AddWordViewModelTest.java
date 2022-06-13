package com.baec.vocabularyquiz.main.ui.addword;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.baec.vocabularyquiz.repository.quizword.QuizWordRepositoryTestImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddWordViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    AddWordViewModel addWordViewModel;

    @Before
    public void setUp() {
        QuizWordRepositoryTestImpl quizWordRepository = new QuizWordRepositoryTestImpl();
        quizWordRepository.init();
        addWordViewModel = new AddWordViewModel(quizWordRepository);
    }

    @Test
    public void onWordTextValidButAnswerTextInvalid_validationOkayIsFalse() {
        addWordViewModel.onWordTextChanged("abcdefg");
        addWordViewModel.onAnswerTextChanged("");
        assertThat(addWordViewModel.isValidationOkay().getValue()).isEqualTo(false);
    }

    @Test
    public void onWordTextInvalidButAnswerTextValid_validationOkayIsFalse() {
        addWordViewModel.onWordTextChanged("");
        addWordViewModel.onAnswerTextChanged("abcdefg");
        assertThat(addWordViewModel.isValidationOkay().getValue()).isEqualTo(false);
    }

    @Test
    public void onWordTextInvalidAndAnswerTextInvalid_validationOkayIsFalse() {
        addWordViewModel.onWordTextChanged("");
        addWordViewModel.onAnswerTextChanged("");
        assertThat(addWordViewModel.isValidationOkay().getValue()).isEqualTo(false);
    }

    @Test
    public void onWordTextValidAndAnswerTextValid_validationOkayIsTrue() {
        addWordViewModel.onWordTextChanged("abcd");
        addWordViewModel.onAnswerTextChanged("abcd");
        assertThat(addWordViewModel.isValidationOkay().getValue()).isEqualTo(true);
    }
}