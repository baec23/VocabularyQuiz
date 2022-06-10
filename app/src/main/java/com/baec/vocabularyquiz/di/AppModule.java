package com.baec.vocabularyquiz.di;

import com.baec.vocabularyquiz.repository.QuizWordRepository;
import com.baec.vocabularyquiz.repository.QuizWordRepositoryImpl;
import com.baec.vocabularyquiz.repository.UserRepository;
import com.baec.vocabularyquiz.repository.UserRepositoryImpl;
import com.baec.vocabularyquiz.repository.UserRepositoryTestImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public UserRepository provideUserRepository(FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        return new UserRepositoryImpl(firebaseAuth, firebaseFirestore);
    }

    @Singleton
    @Provides
    public QuizWordRepository provideQuizWordRepository(FirebaseFirestore firebaseFirestore) {
        return new QuizWordRepositoryImpl(firebaseFirestore);
    }

    @Singleton
    @Provides
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    public FirebaseFirestore provideFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }
}
