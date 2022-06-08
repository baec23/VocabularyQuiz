package com.baec.vocabularyquiz;

import com.baec.vocabularyquiz.model.User;
import com.baec.vocabularyquiz.util.Result;

public interface UserRepository {
    Result<User> tryLogin(String username, String password);
}
