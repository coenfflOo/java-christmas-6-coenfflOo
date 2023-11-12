package christmas.domain.constant;

public enum DiscountItem {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKLY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    PROMOTION_DISCOUNT("증정 이벤트");

    private final String description;

    DiscountItem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
