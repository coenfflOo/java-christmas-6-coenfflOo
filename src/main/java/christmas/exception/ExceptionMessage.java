package christmas.exception;

public enum ExceptionMessage {
    ERROR("[ERROR] "),
    IS_INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    IS_INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
