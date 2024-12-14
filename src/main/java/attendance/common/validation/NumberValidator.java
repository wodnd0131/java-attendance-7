package attendance.common.validation;

public final class NumberValidator {
    private NumberValidator() {
        throw new AssertionError();
    }

    /**
     * 정수 여부를 검사합니다.
     */
    public static boolean isInteger(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 실수 여부를 검사합니다.
     */
    public static boolean isDouble(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 주어진 범위 내의 정수인지 검사합니다.
     */
    public static boolean isIntegerInRange(String str, int min, int max) {
        if (!isInteger(str)) {
            return false;
        }
        int value = Integer.parseInt(str);
        return value >= min && value <= max;
    }

    /**
     * 양수 여부를 검사합니다.
     */
    public static boolean isPositive(String str) {
        if (!isDouble(str)) {
            return false;
        }
        return Double.parseDouble(str) > 0;
    }

    /**
     * 음수 여부를 검사합니다.
     */
    public static boolean isNegative(String str) {
        if (!isDouble(str)) {
            return false;
        }
        return Double.parseDouble(str) < 0;
    }

    /**
     * 숫자 형식이 올바른지 검사합니다 (콤마, 소수점 등 포함).
     */
    public static boolean isValidNumberFormat(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return str.matches("^-?\\d*\\.?\\d+$");
    }
}