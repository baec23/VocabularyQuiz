package com.baec.vocabularyquiz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isUsernameValid(String username) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(regexPattern);
        Matcher m = p.matcher(username);
        return m.matches();
    }

    public static boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length() > 30)
            return false;
        return true;
    }
}
