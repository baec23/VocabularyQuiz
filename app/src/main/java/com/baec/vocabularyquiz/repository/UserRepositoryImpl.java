package com.baec.vocabularyquiz.repository;

import static com.baec.vocabularyquiz.util.Constants.USER_COLLECTION_NAME;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepositoryImpl implements UserRepository {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public UserRepositoryImpl(FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void tryLogin(String username, String password, RepositoryCallback<Result<User>> callback) {
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser fbUser = task.getResult().getUser();
                callback.onComplete(new Result.Success<User>(new User(fbUser.getUid(), fbUser.getEmail())));
            } else {
                callback.onComplete(new Result.Error(new Exception("Couldn't log in")));
            }
        });
    }

    @Override
    public void tryRegister(String username, String password, RepositoryCallback<Result<User>> callback) {
        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser fbUser = task.getResult().getUser();
                User newUser = new User(fbUser.getUid(), fbUser.getEmail());
                firebaseFirestore.collection(USER_COLLECTION_NAME).add(newUser).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful())
                        callback.onComplete(new Result.Success<User>(newUser));
                    else
                        callback.onComplete(new Result.Error(new Exception("Error creating user")));
                });
            } else {
                callback.onComplete(new Result.Error(new Exception("Error creating user")));
            }
        });
    }
}
