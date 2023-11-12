package christmas.domain;

import christmas.constant.Message.ExceptionMessage;

public class Date {

    private final int date;

    private Date(int date) {
        validateMinimumDate(date);
        validateMaximumDate(date);
        this.date = date;
    }

    // Static Factory Method
    public static Date from(final int date) {
        return new Date(date);
    }



    private void validateMinimumDate(final int date) {
        if (isSmallerThanUnitDate(date)) {
            throw new ArithmeticException(); // 1보다 작음
        }
    }

    private void validateMaximumDate(final int date) {
        if (isBiggerThanMaximumDate(date)) {
            throw new ArithmeticException(); //31보다 큼
        }
    }

    private boolean isBiggerThanMaximumDate(final int date) {
        return date > 31; // 상수처리
    }

    private boolean isSmallerThanUnitDate(final int date) {
        return date < 1; // 상수처리
    }


    public int getDate() {
        return date;
    }
}
