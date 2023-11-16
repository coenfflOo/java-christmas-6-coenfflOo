package christmas.domain;

import static christmas.util.DateUtil.isNotDayAMonth;

import christmas.exception.ChristmasException;

public class Date {

    private final int date;

    private Date(int date) {
        validateDateOfMonth(date);
        this.date = date;
    }

    // Static Factory Method
    public static Date from(final int date) {
        return new Date(date);
    }


    private void validateDateOfMonth(final int date) {
        if (isNotDayAMonth(date)) {
            throw ChristmasException.invalidDate(new IllegalArgumentException());
        }
    }

    public int getDate() {
        return date;
    }
}
