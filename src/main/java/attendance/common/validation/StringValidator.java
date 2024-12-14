package attendance.common.validation;

public final class StringValidator {
    private StringValidator() {
        throw new AssertionError();
    }

    /**
     * null 또는 빈 문자열("") 여부를 검사합니다.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * null, 빈 문자열(""), 또는 공백 문자로만 이루어진 문자열 여부를 검사합니다.
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().isEmpty();
    }

    /**
     * 문자열에 공백이 포함되어 있는지 검사합니다.
     */
    public static boolean containsWhitespace(String str) {
        if (str == null) {
            return false;
        }
        return str.matches(".*\\s.*");
    }

    /**
     * 문자열이 오직 공백 문자로만 이루어져 있는지 검사합니다.
     */
    public static boolean isOnlyWhitespace(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^\\s*$");
    }

    /**
     * 문자열이 알파벳으로만 이루어져 있는지 검사합니다.
     */
    public static boolean isAlphabetic(String str) {
        if (isBlank(str)) {
            return false;
        }
        return str.matches("^[a-zA-Z]*$");
    }

    /**
     * 문자열이 알파벳과 숫자로만 이루어져 있는지 검사합니다.
     */
    public static boolean isAlphanumeric(String str) {
        if (isBlank(str)) {
            return false;
        }
        return str.matches("^[a-zA-Z0-9]*$");
    }

    /**
     * 문자열이 지정된 길이 범위 내에 있는지 검사합니다.
     */
    public static boolean isLengthInRange(String str, int minLength, int maxLength) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        return length >= minLength && length <= maxLength;
    }

    /**
     * 문자열에 특수문자가 포함되어 있는지 검사합니다.
     */
    public static boolean containsSpecialCharacters(String str) {
        if (str == null) {
            return false;
        }
        return str.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    /**
     * 문자열이 이메일 형식인지 검사합니다.
     */
    public static boolean isEmailFormat(String str) {
        if (isBlank(str)) {
            return false;
        }
        return str.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}