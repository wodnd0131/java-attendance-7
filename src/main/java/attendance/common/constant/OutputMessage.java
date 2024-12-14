package attendance.common.constant;

public enum OutputMessage {
    CHECK_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
    CHECK_OTHER_PURCHASE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"), // 자동 줄바꿈

    STOCK_INFO("- %s %,d원 %s개 %s"), // 줄바꿈 요구 %s 는 문자열 주입, %,d 는 n_nnn 형식 숫자 주입( 2,000)
    STOCK_SOLD_OUT_INFO("- %s %,d원 재고 없음"),
    ;
    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}