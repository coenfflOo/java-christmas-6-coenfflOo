package christmas.domain.constant;

import christmas.view.constant.OutputMessage;

public enum BadgeItem {
    SANTA("산타", 20_000),
    TREE("나무", 10_000),
    STAR("별", 5_000);

    private final String icon;
    private final int standard;

    BadgeItem(String icon, int standard) {
        this.icon = icon;
        this.standard = standard;
    }

    public static String getIconForValue(int value) {
        for (BadgeItem badgeItem : values()) {
            if (value >= badgeItem.standard) {
                return badgeItem.icon;
            }
        }
        return OutputMessage.NOTHING.getMessage();
    }
}
