package christmas.exception;

public enum ErrorMessage {
    ERROR_CAPTION("[ERROR] "),
    INVALID_VISITING_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_BADGE_FINDER_PARAMETER("유효하지 않은 총혜택금액 입니다."),
    INVALID_GIVEAWAY_BENEFIT_AMOUNT("증정 이벤트의 상품 가격이 유효하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_CAPTION.message + message;
    }
}
