package christmas.service;

import static christmas.util.DateUtil.isBeforeChristmasDay;

import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.constant.DiscountItem;

public class ChristmasDDayService {
    private static final int DAILY_DISCOUNT = 100;
    private static final int FIRST_DAY_DISCOUNT = 1000;
    int christmasDiscount;

    public int checkChristmasDiscount(Date date, Discount discount) {
        calculateTotalDiscount(date);
        discount.addEventApplied(DiscountItem.CHRISTMAS_DDAY_DISCOUNT,christmasDiscount);
        return christmasDiscount;
    }

    private void calculateTotalDiscount(Date date) {
        this.christmasDiscount = calculateChristmasDiscount(date);
    }

    private int calculateChristmasDiscount(Date date) {
        if (isBeforeChristmasDay(date.getDate()))
            return calculateDailyDiscount(date.getDate());
        return 0;
    }

    private static int calculateDailyDiscount(int daysUntilChristmas) {
        return 1000 + ((FIRST_DAY_DISCOUNT-1) * DAILY_DISCOUNT);
    }
}
