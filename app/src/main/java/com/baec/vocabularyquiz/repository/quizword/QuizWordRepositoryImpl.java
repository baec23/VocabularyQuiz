package com.baec.vocabularyquiz.repository.quizword;

import static com.baec.vocabularyquiz.util.Constants.QUIZWORD_COLLECTION_NAME;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.baec.vocabularyquiz.model.WordAnswer;
import com.baec.vocabularyquiz.model.QuizWord;
import com.baec.vocabularyquiz.repository.RepositoryCallback;
import com.baec.vocabularyquiz.util.Result;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuizWordRepositoryImpl implements QuizWordRepository {
    FirebaseFirestore firebaseFirestore;

    private List<QuizWord> quizWords = new ArrayList<>();
    private List<String> potentialAnswers = new ArrayList<>();
    private MutableLiveData<Boolean> wordsLoaded = new MutableLiveData<>(false);

    public QuizWordRepositoryImpl(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    @Override
    public QuizWord getRandomQuizWord() {
        Random r = new Random();
        return quizWords.get(r.nextInt(quizWords.size()));
    }

    @Override
    public List<WordAnswer> getAnswers(QuizWord quizWord, int numTotalAnswers) {
        List<WordAnswer> toReturn = new ArrayList<>();
        toReturn.add(new WordAnswer(quizWord.getAnswer(), WordAnswer.AnswerGuessState.NOT_GUESSED));
        Random r = new Random();
        Set<String> answerSet = new HashSet<>();
        answerSet.add(quizWord.getAnswer());
        while (answerSet.size() < numTotalAnswers) {
            String answer = potentialAnswers.get(r.nextInt(potentialAnswers.size()));
            if (!answerSet.contains(answer)) {
                toReturn.add(new WordAnswer(answer, WordAnswer.AnswerGuessState.NOT_GUESSED));
                answerSet.add(answer);
            }
        }
        return toReturn;
    }

    @Override
    public void addQuizWord(QuizWord quizWord, RepositoryCallback<Result<String>> callback) {
        firebaseFirestore.collection(QUIZWORD_COLLECTION_NAME).add(quizWord).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onComplete(new Result.Success<String>("Added word!"));
            } else {
                callback.onComplete(new Result.Error(new Exception("Failed to add word!")));
            }
        });
    }

    public void init() {
        firebaseFirestore.collection(QUIZWORD_COLLECTION_NAME).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    List<DocumentSnapshot> documentsList = value.getDocuments();
                    List<QuizWord> newQuizWordsList = new ArrayList<>();
                    List<String> newPotentialAnswersList = new ArrayList<>();
                    for (DocumentSnapshot doc : documentsList) {
                        QuizWord quizWord = doc.toObject(QuizWord.class);
                        newQuizWordsList.add(quizWord);
                        newPotentialAnswersList.add(quizWord.getAnswer());
                    }
                    quizWords = newQuizWordsList;
                    potentialAnswers = newPotentialAnswersList;
                    wordsLoaded.postValue(true);
                }
            }
        });
    }

    public LiveData<Boolean> isLoaded() {
        return wordsLoaded;
    }
}
