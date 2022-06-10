package com.baec.vocabularyquiz.repository;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;

public interface UserRepository {
    void tryLogin(String username, String password, RepositoryCallback<Result<User>> callback);
    void tryRegister(String username, String password, RepositoryCallback<Result<User>> callback);
}
