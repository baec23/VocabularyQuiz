package com.baec.vocabularyquiz.repository;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;

public class UserRepositoryTestImpl implements UserRepository {
    @Override
    public void tryLogin(String username, String password, RepositoryCallback<Result<User>> callback) {

    }

    @Override
    public void tryRegister(String username, String password, RepositoryCallback<Result<User>> callback) {

    }
}
