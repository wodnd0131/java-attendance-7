package attendance.common.constant;

public enum ErrorMessages {
    ERROR_PREFIX("[ERROR] "),
    INVALID_OUT_OF_INT_RANGE("입력된 숫자가 정수 범위를 벗어났습니다."),
    INVALID_IS_NULL("필수 입력값이 비어있습니다. 값을 입력해주세요."),
    INVALID_NOT_NUMERIC("숫자만 입력 가능합니다. 다시 입력해주세요."),

    RESOURCE_FAILED_READ("저장소의 데이터를 읽을 수 없습니다 : "),
    RESOURCE_NOT_FOUND("저장소가 존재하지 않습니다 : "),

    INVALID_INPUT_FORMAT("잘못된 형식을 입력하였습니다."),
    WRONG_NICKNAME("등록되지 않은 닉네임입니다."),
    CANT_MODIFICATION("아직 수정할 수 없습니다."),
    NOT_OPERATING_HOUR("캠퍼스 운영 시간에만 출석이 가능합니다."),
    ALREADY_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),
    ;

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return ERROR_PREFIX.getMessage() + message;
    }
}
