package attendance.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import camp.nextstep.edu.missionutils.DateTimes;

public final class DateTimesWrapper {
    private static final String DATE_PATTERN = "MM월 dd일 E요일";
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

    public static LocalDateTime toLocalDateTime(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
        return date.atStartOfDay();  // 시간을 00:00:00으로 설정
    }
}