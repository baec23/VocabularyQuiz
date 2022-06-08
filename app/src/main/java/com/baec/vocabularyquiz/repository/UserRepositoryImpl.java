package com.baec.vocabularyquiz.repository;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;

public class UserRepositoryImpl implements UserRepository {
    @SuppressWarnings("unchecked")
    @Override
    public Result<User> tryLogin(String username, String password) {
        if(username.equals("HELLO"))
            return new Result.Success<User>(new User());
        else
            return new Result.Error(new Exception("NOPE"));
    }
}
