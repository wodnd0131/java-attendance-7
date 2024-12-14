package attendance.common.vaildation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import oncall.common.validation.StringValidator;

class StringValidatorTest {

    @Test
    void 빈_문자열_검증() {
        // Given & When & Then
        assertTrue(StringValidator.isEmpty(null));
        assertTrue(StringValidator.isEmpty(""));
        assertFalse(StringValidator.isEmpty(" "));
        assertFalse(StringValidator.isEmpty("test"));
        assertFalse(StringValidator.isEmpty(" test "));
    }

    @Test
    void 공백_문자열_검증() {
        // Given & When & Then
        assertTrue(StringValidator.isBlank(null));
        assertTrue(StringValidator.isBlank(""));
        assertTrue(StringValidator.isBlank(" "));
        assertTrue(StringValidator.isBlank("   "));
        assertFalse(StringValidator.isBlank("test"));
        assertFalse(StringValidator.isBlank(" test "));
    }

    @Test
    void 공백_포함_검증() {
        // Given & When & Then
        assertFalse(StringValidator.containsWhitespace(null));
        assertFalse(StringValidator.containsWhitespace(""));
        assertTrue(StringValidator.containsWhitespace(" "));
        assertTrue(StringValidator.containsWhitespace(" test"));
        assertTrue(StringValidator.containsWhitespace("test test"));
        assertTrue(StringValidator.containsWhitespace("test "));
        assertFalse(StringValidator.containsWhitespace("test"));
    }

    @Test
    void 순수_공백_문자열_검증() {
        // Given & When & Then
        assertFalse(StringValidator.isOnlyWhitespace(null));
        assertTrue(StringValidator.isOnlyWhitespace(""));
        assertTrue(StringValidator.isOnlyWhitespace(" "));
        assertTrue(StringValidator.isOnlyWhitespace("   "));
        assertFalse(StringValidator.isOnlyWhitespace("test"));
        assertFalse(StringValidator.isOnlyWhitespace(" test "));
    }

    @Test
    void 알파벳_문자열_검증() {
        // Given & When & Then
        assertFalse(StringValidator.isAlphabetic(null));
        assertFalse(StringValidator.isAlphabetic(""));
        assertFalse(StringValidator.isAlphabetic(" "));
        assertTrue(StringValidator.isAlphabetic("test"));
        assertTrue(StringValidator.isAlphabetic("TEST"));
        assertTrue(StringValidator.isAlphabetic("TestTest"));
        assertFalse(StringValidator.isAlphabetic("test123"));
        assertFalse(StringValidator.isAlphabetic("test!"));
    }

    @Test
    void 알파벳_숫자_문자열_검증() {
        // Given & When & Then
        assertFalse(StringValidator.isAlphanumeric(null));
        assertFalse(StringValidator.isAlphanumeric(""));
        assertFalse(StringValidator.isAlphanumeric(" "));
        assertTrue(StringValidator.isAlphanumeric("test123"));
        assertTrue(StringValidator.isAlphanumeric("123test"));
        assertTrue(StringValidator.isAlphanumeric("TEST123"));
        assertFalse(StringValidator.isAlphanumeric("test 123"));
        assertFalse(StringValidator.isAlphanumeric("test!123"));
    }

    @Test
    void 문자열_길이_범위_검증() {
        // Given
        int minLength = 2;
        int maxLength = 5;

        // When & Then
        assertFalse(StringValidator.isLengthInRange(null, minLength, maxLength));
        assertFalse(StringValidator.isLengthInRange("a", minLength, maxLength));
        assertTrue(StringValidator.isLengthInRange("ab", minLength, maxLength));
        assertTrue(StringValidator.isLengthInRange("abc", minLength, maxLength));
        assertTrue(StringValidator.isLengthInRange("abcde", minLength, maxLength));
        assertFalse(StringValidator.isLengthInRange("abcdef", minLength, maxLength));
    }

    @Test
    void 특수문자_포함_검증() {
        // Given & When & Then
        assertFalse(StringValidator.containsSpecialCharacters(null));
        assertFalse(StringValidator.containsSpecialCharacters(""));
        assertFalse(StringValidator.containsSpecialCharacters("test"));
        assertFalse(StringValidator.containsSpecialCharacters("test123"));
        assertTrue(StringValidator.containsSpecialCharacters("test!"));
        assertTrue(StringValidator.containsSpecialCharacters("@test"));
        assertTrue(StringValidator.containsSpecialCharacters("test#test"));
        assertTrue(StringValidator.containsSpecialCharacters("test$"));
    }

    @Test
    void 이메일_형식_검증() {
        // Given
        String[] validEmails = {
            "test@test.com",
            "user@domain.co.kr",
            "user.name@domain.com",
            "user+name@domain.com",
            "123@domain.com"
        };

        String[] invalidEmails = {
            null,
            "",
            " ",
            "test",
            "@domain.com",
            "test@",
            "test@domain",
            "test.domain.com",
            "test@domain@com",
            "test space@domain.com"
        };

        // When & Then
        for (String validEmail : validEmails) {
            assertTrue(StringValidator.isEmailFormat(validEmail));
        }

        for (String invalidEmail : invalidEmails) {
            assertFalse(StringValidator.isEmailFormat(invalidEmail));
        }
    }
}
