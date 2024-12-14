package attendance.common.vaildation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import attendance.common.constant.ErrorMessages;
import attendance.common.validation.InputValidator;

class InputValidatorTest {

    @Test
    void 정상적인_숫자_입력_검증() {
        // Given
        String[] validInputs = {
            "0",
            "42",
            "-10",
            "2147483647",  // Integer.MAX_VALUE
            "-2147483648"  // Integer.MIN_VALUE
        };

        // When & Then
        for (String input : validInputs) {
            int result = InputValidator.validateInput(input);
            assertEquals(Integer.parseInt(input), result);
        }
    }

    @Test
    void 빈_문자열_입력시_예외_발생() {
        // Given
        String[] emptyInputs = {
            "",
            " ",
            "   "
        };

        // When & Then
        for (String input : emptyInputs) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> InputValidator.validateInput(input)
            );
            assertEquals(ErrorMessages.INVALID_IS_NULL.getMessage(), exception.getMessage());
        }
    }

    @Test
    void 숫자가_아닌_입력시_예외_발생() {
        // Given
        String[] nonNumericInputs = {
            "abc",
            "12.34",
            "10a",
            "a10",
            "!@#"
        };

        // When & Then
        for (String input : nonNumericInputs) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> InputValidator.validateInput(input)
            );
            assertEquals(ErrorMessages.INVALID_NOT_NUMERIC.getMessage(), exception.getMessage());
        }
    }

    @Test
    void 정수_범위_초과시_예외_발생() {
        // Given
        String[] outOfRangeInputs = {
            "2147483648",  // Integer.MAX_VALUE + 1
            "-2147483649", // Integer.MIN_VALUE - 1
            "9999999999"   // 매우 큰 수
        };

        // When & Then
        for (String input : outOfRangeInputs) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> InputValidator.validateInput(input)
            );
            assertEquals(ErrorMessages.INVALID_OUT_OF_INT_RANGE.getMessage(), exception.getMessage());
        }
    }

}