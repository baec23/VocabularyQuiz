package com.baec.vocabularyquiz.repository;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    FirebaseFirestore firebaseFirestore;

    @SuppressWarnings("unchecked")
    @Override
    public void tryLogin(String username, String password, RepositoryCallback<Result<User>> callback) {
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser fbUser = task.getResult().getUser();
            } else {

            }
        });
        if (username.equals("HELLO"))
            callback.onComplete(new Result.Success<User>(new User()));
        else
            callback.onComplete(new Result.Error(new Exception("Something wrong")));
    }

    @Override
    public void tryRegister(String username, String password, RepositoryCallback<Result<User>> callback) {
        callback.onComplete(new Result.Success<User>(new User()));
    }
}
