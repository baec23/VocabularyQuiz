package com.baec.vocabularyquiz.util;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class InputValidatorTest {
    @Test
    public void usernameIsEmpty_false() {
        assertThat(InputValidator.isUsernameValid("")).isFalse();
    }

    @Test
    public void usernameIsTooShort_false() {
        assertThat(InputValidator.isUsernameValid("abc")).isFalse();
    }

    @Test
    public void usernameContainsSpecialCharacters_false() {
        assertThat(InputValidator.isUsernameValid("a@bcdefg!")).isFalse();
    }

    @Test
    public void usernameIsTooLong_false() {
        assertThat(InputValidator.isUsernameValid("aabcdefghijklmnopqrstuvwxyzabcdefghijklmnop")).isFalse();
    }

    @Test
    public void usernameIsValid_true() {
        assertThat(InputValidator.isUsernameValid("abCdEfg")).isTrue();
    }

    @Test
    public void passwordIsTooShort_false() {
        assertThat(InputValidator.isPasswordValid("abCdEfg")).isFalse();
    }

    @Test
    public void passwordIsTooLong_false() {
        assertThat(InputValidator.isPasswordValid("abcdefghijklmnopqrstuvwxyzabcdefghijklmnop")).isFalse();
    }

    @Test
    public void passwordIsBetween8And30Characters_true() {
        assertThat(InputValidator.isPasswordValid("abcdefg$!@#5A")).isTrue();
    }
}