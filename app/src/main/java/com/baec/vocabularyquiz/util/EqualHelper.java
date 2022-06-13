package com.baec.vocabularyquiz.util;

public class EqualHelper {
    public static boolean equalHelper(Object a, Object b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        else
            return a.equals(b);
    }
}
