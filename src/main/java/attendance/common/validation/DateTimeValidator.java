package attendance.common.validation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    public static String checkLateness(LocalTime time) {

        LocalTime startTime = LocalTime.of(9, 59, 59, 0);
        LocalTime lateness = LocalTime.of(10, 4, 59, 0);
        LocalTime absence = LocalTime.of(10, 29, 59, 0);
        if (time.isAfter(startTime) && time.isBefore(lateness)) {
            return "출석";
        }
        if (time.isAfter(lateness) && time.isBefore(absence)) {
            return "지각";
        }
        return "결석";
    }
}