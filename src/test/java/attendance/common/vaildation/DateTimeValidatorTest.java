package attendance.common.vaildation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import attendance.common.validation.DateTimeValidator;

class DateTimeValidatorTest {

    @Test
    void 날짜_패턴_검증_성공() {
        // Given
        String validDate = "2024-12-25";

        // When
        boolean isValid = DateTimeValidator.isValidDatePattern(validDate);

        // Then
        assertTrue(isValid);
    }

    @Test
    void 잘못된_날짜_패턴_검증_실패() {
        // Given
        String[] invalidDates = {
            "2024/12/25",  // 잘못된 구분자
            "24-12-25",    // 잘못된 년도 형식
            "2024-1-1",    // 잘못된 월일 형식
            "2024.12.25",  // 잘못된 구분자
            "",            // 빈 문자열
            "invalid"      // 완전히 잘못된 형식
        };

        // When & Then
        for (String invalidDate : invalidDates) {
            assertFalse(DateTimeValidator.isValidDatePattern(invalidDate));
        }
    }

    @Test
    void 날짜_이후_비교_검증() {
        // Given
        LocalDateTime earlier = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime later = LocalDateTime.of(2024, 12, 31, 0, 0);

        // When & Then
        assertTrue(DateTimeValidator.isDateAfter(later, earlier));
        assertFalse(DateTimeValidator.isDateAfter(earlier, later));
    }

    @Test
    void 동일_날짜_이후_비교_검증() {
        // Given
        LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime sameDate = LocalDateTime.of(2024, 1, 1, 0, 0);

        // When & Then
        assertFalse(DateTimeValidator.isDateAfter(date, sameDate));
    }

    @Test
    void 날짜_이전_비교_검증() {
        // Given
        LocalDateTime earlier = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime later = LocalDateTime.of(2024, 12, 31, 0, 0);

        // When & Then
        assertTrue(DateTimeValidator.isDateBefore(earlier, later));
        assertFalse(DateTimeValidator.isDateBefore(later, earlier));
    }

    @Test
    void 동일_날짜_이전_비교_검증() {
        // Given
        LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime sameDate = LocalDateTime.of(2024, 1, 1, 0, 0);

        // When & Then
        assertFalse(DateTimeValidator.isDateBefore(date, sameDate));
    }

}