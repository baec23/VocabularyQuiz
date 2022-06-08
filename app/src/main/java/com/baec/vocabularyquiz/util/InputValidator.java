package com.baec.vocabularyquiz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isUsernameValid(String username) {
        if (username.length() <= 4 || username.length() >= 30)
            return false;
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(username);
        return !m.find();
    }

    public static boolean isPasswordValid(String password) {
        if (password.length() <= 8 || password.length() >= 30)
            return false;
        return true;
    }
}
