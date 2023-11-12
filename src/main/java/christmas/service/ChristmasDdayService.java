package christmas.service;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.constant.DiscountItem;
import christmas.util.DateUtil;

public class ChristmasDdayService {
    int christmasDiscount ;

    public int checkChristmasDiscount(Date date, Discount discount) {
        calculateTotalDiscount(date);
        discount.addEventApplied(DiscountItem.CHRISTMAS_DDAY_DISCOUNT,christmasDiscount);
        return christmasDiscount;
    }

    private void calculateTotalDiscount(Date date) {
        this.christmasDiscount = 0;
        christmasDiscount -= calculateChristmasDiscount(date);
    }

    private int calculateChristmasDiscount(Date date) {
        if (isChristmasDay(date.getDate()))
            return calculateDailyDiscount(date.getDate());
        return 0;
    }

    private static boolean isChristmasDay(final int date) {
        return date >= 1 && date <= 25; // 상수처리
    }

    private static int calculateDailyDiscount(int daysUntilChristmas) {
        return 1000 + ((daysUntilChristmas-1) * 100);
    }
}
