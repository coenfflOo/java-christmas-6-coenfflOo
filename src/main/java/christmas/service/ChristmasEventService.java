package christmas.service;

import christmas.domain.Date;
import christmas.domain.Menu;

public class ChristmasEventService {
    int christmasDiscount;

    public int calculateTotalDiscount(Date date, Menu menu) {
        this.christmasDiscount -= calculateChristmasDiscount(date);
        // 다른 할인 계산 로직 추가

        return christmasDiscount;
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
