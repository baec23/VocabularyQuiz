package com.baec.vocabularyquiz.repository.user;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.repository.RepositoryCallback;
import com.baec.vocabularyquiz.util.Result;

public class UserRepositoryTestImpl implements UserRepository {

    public UserRepositoryTestImpl() {

    }

    @Override
    public void tryLogin(String username, String password, RepositoryCallback<Result<User>> callback) {
        if (username.equals("test@test.com"))
            callback.onComplete(new Result.Success<User>(new User("a", "test@test.com")));
        else
            callback.onComplete(new Result.Error(new Exception("Failed")));
    }

    @Override
    public void tryRegister(String username, String password, RepositoryCallback<Result<User>> callback) {
        if (username.equals("test@test.com"))
            callback.onComplete(new Result.Success<User>(new User("a", "test@test.com")));
        else
            callback.onComplete(new Result.Error(new Exception("Failed")));
    }
}
