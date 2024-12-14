package attendance.common.constant;

public enum OutputMessage {
    WRITE_NAME("닉네임을 입력해 주세요."),
    WRITE_TIME("등교 시간을 입력해 주세요."),
    MODIFY_NAME("출석을 수정하려는 크루의 닉네임을 입력해 주세요."),
    MODIFY_DAY("수정하려는 날짜(일)를 입력해 주세요."),
    MODIFY_TO_WHEN("언제로 변경하겠습니까?"),

    OPENING_MESSAGE("오늘은 %s입니다. 기능을 선택해 주세요.\n"
        + "1. 출석 확인\n"
        + "2. 출석 수정\n"
        + "3. 크루별 출석 기록 확인\n"
        + "4. 제적 위험자 확인\n"
        + "Q. 종료\n"),
    TODAY_NOT_OPERATING_HOUR("%s은 등교일이 아닙니다."),

    SHOW_ATTENDANCE_RESULT("%s %s %s"),
    MODIFY_SUCCESS("%s %s %s -> %s %s 수정 완료!"),
    RECORD("%s %s %s"),
    SHOW_ATTENDANCE_LIST("이번 달 %s의 출석 기록입니다."),
    ;
    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(String input) {
        return String.format(message, input);
    }
}