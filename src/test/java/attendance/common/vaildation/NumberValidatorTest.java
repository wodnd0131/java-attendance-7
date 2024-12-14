package attendance.common.vaildation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import oncall.common.validation.NumberValidator;

class NumberValidatorTest {

    @Test
    void 정수_판별_검증() {
        // Given
        String[] validIntegers = {"0", "42", "-10", "2147483647", "-2147483648"};
        String[] invalidIntegers = {null, "", " ", "3.14", "abc", "12.0", "10a"};

        // When & Then
        for (String valid : validIntegers) {
            assertTrue(NumberValidator.isInteger(valid));
        }

        for (String invalid : invalidIntegers) {
            assertFalse(NumberValidator.isInteger(invalid));
        }
    }

    @Test
    void 실수_판별_검증() {
        // Given
        String[] validDoubles = {
            "0", "42", "-10", "3.14", "0.0",
            "-2.5", "1.0", "2147483647.0"
        };
        String[] invalidDoubles = {
            null, "", " ", "abc", "12.a",
            "a.12", "1,000.0"
        };

        // When & Then
        for (String valid : validDoubles) {
            assertTrue(NumberValidator.isDouble(valid));
        }

        for (String invalid : invalidDoubles) {
            assertFalse(NumberValidator.isDouble(invalid));
        }
    }

    @Test
    void 정수_범위_검증() {
        // Given
        int min = 1;
        int max = 100;

        String[] validRange = {"1", "50", "100"};
        String[] invalidRange = {
            "0", "101", "-1", "abc", null,
            "", " ", "3.14"
        };

        // When & Then
        for (String valid : validRange) {
            assertTrue(NumberValidator.isIntegerInRange(valid, min, max));
        }

        for (String invalid : invalidRange) {
            assertFalse(NumberValidator.isIntegerInRange(invalid, min, max));
        }
    }

    @Test
    void 양수_판별_검증() {
        // Given
        String[] positives = {"1", "0.1", "2.5", "1000"};
        String[] nonPositives = {
            "0", "-1", "-0.1", "abc", null,
            "", " ", "-2.5"
        };

        // When & Then
        for (String positive : positives) {
            assertTrue(NumberValidator.isPositive(positive));
        }

        for (String nonPositive : nonPositives) {
            assertFalse(NumberValidator.isPositive(nonPositive));
        }
    }

    @Test
    void 음수_판별_검증() {
        // Given
        String[] negatives = {"-1", "-0.1", "-2.5", "-1000"};
        String[] nonNegatives = {
            "0", "1", "0.1", "abc", null,
            "", " ", "2.5"
        };

        // When & Then
        for (String negative : negatives) {
            assertTrue(NumberValidator.isNegative(negative));
        }

        for (String nonNegative : nonNegatives) {
            assertFalse(NumberValidator.isNegative(nonNegative));
        }
    }

    @Test
    void 숫자_형식_검증() {
        // Given
        String[] validFormats = {
            "1", "-1", "0", "42", "3.14",
            "-2.5", "0.0", "1.0"
        };
        String[] invalidFormats = {
            null, "", " ", "abc", "1,000",
            "1.2.3", "1a", "a1", ".5", "1."
        };

        // When & Then
        for (String valid : validFormats) {
            assertTrue(NumberValidator.isValidNumberFormat(valid));
        }

        for (String invalid : invalidFormats) {
            assertFalse(NumberValidator.isValidNumberFormat(invalid));
        }
    }

}
