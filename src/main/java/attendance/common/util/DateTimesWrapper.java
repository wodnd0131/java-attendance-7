package attendance.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import camp.nextstep.edu.missionutils.DateTimes;

public final class DateTimesWrapper {
    private static final String DATE_PATTERN = "MM월 dd일 E요일";
    private static final String DATE_PATTERN_CSV = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private DateTimesWrapper() {
        throw new AssertionError();
    }

    public static LocalDateTime now() {
        return DateTimes.now();
    }

    public static String nowToString() {
        return now().format(DATE_FORMATTER);
    }

    public static String dateToString(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static LocalDateTime toLocalDateTime(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
        return date.atStartOfDay();
    }

    public static LocalDate toLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN_CSV));
    }

    public static LocalTime toLocalTime(String dateStr) {
        return LocalTime.parse(dateStr, DateTimeFormatter.ofPattern(TIME_PATTERN));
    }
}