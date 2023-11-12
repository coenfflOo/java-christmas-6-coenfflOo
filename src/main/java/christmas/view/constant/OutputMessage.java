package christmas.view.constant;

public enum OutputMessage {
    RESPONSE_MENU("<주문 메뉴>"),
    RESPONSE_PRICE("<할인 전 총주문 금액>"),
    RESPONSE_GIFT("<증정 메뉴>"),
    RESPONSE_BENEFIT("<혜택 내역>"),
    RESPONSE_DISCOUNT("<총혜택 금액>"),
    RESPONSE_DC_PRICE("<할인 후 예상 결제 금액>"),
    RESPONSE_BADGE("<12월 이벤트 배지>")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
