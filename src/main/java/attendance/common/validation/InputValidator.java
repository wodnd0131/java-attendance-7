package attendance.common.validation;

import static attendance.common.constant.ErrorMessages.*;

import java.util.regex.Pattern;

public final class InputValidator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    private InputValidator() {
        throw new AssertionError();
    }

    public static int validateInput(String input) {
        validateNull(input);
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String input) {
        validateIsNumeric(input);
        validateRange(input);
    }

    public static void validateNull(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INVALID_IS_NULL.getMessage());
        }
    }

    private static void validateIsNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(INVALID_NOT_NUMERIC.getMessage());
        }
    }

    private static void validateRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_OUT_OF_INT_RANGE.getMessage());
        }
    }

    public static void validateInputRange(String input) {
        int i = Integer.parseInt(input);
        if (i < 1 || i >= 5) {
            throw new IllegalArgumentException(INVALID_OUT_OF_INT_RANGE.getMessage());
        }
    }

}