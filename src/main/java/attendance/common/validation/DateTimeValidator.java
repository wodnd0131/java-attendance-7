package attendance.common.validation;

import java.time.LocalDateTime;

public final class DateTimeValidator {
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    private DateTimeValidator() {
        throw new AssertionError();
    }

    public static boolean isValidDatePattern(String input) {
        return input.matches(DATE_PATTERN);
    }

    public static boolean isDateAfter(LocalDateTime first, LocalDateTime second) {
        return first.isAfter(second);
    }

    public static boolean isDateBefore(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second);
    }
}