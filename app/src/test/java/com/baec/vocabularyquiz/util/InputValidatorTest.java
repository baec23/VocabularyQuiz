package com.baec.vocabularyquiz.util;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class InputValidatorTest {

    @Test
    public void usernameNotEmail_false() {
        assertThat(InputValidator.isUsernameValid("abcdefg")).isFalse();
        assertThat(InputValidator.isUsernameValid("abc@abc")).isFalse();
        assertThat(InputValidator.isUsernameValid("abcbc.com")).isFalse();
    }

    @Test
    public void usernameIsValid_true() {
        assertThat(InputValidator.isUsernameValid("test@test.com")).isTrue();
        assertThat(InputValidator.isUsernameValid("test@test.org")).isTrue();
        assertThat(InputValidator.isUsernameValid("test@test.co.kr")).isTrue();
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