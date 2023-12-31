package christmas.exception;

public enum ErrorMessage {
    ERROR_CAPTION("[ERROR] "),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDERS("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_CAPTION.message + message;
    }
}