package christmas.util;

import java.util.Calendar;

public class DateUtil {
    private static final int YEAR = 2023;
    public static final int CHRISTMAS = 25;
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;

    private static boolean isWeekday(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, Calendar.DECEMBER, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY;
    }

    public static int isWeekend(int day) {
        if (!isWeekday(day))
        {
            return 1;
        }
        return 0;
    }

    public static boolean isSpecialDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, Calendar.DECEMBER, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek == Calendar.SUNDAY || day == CHRISTMAS;
    }

    public static boolean isNotDayAMonth(final int date) {
        return isBiggerThanMaximumDate(date) || isSmallerThanUnitDate(date);
    }

    public static boolean isChristmasDay(final int date) {
        return date >= FIRST_DAY && date <= CHRISTMAS;
    }

    private static boolean isBiggerThanMaximumDate(final int date) {
        return date > LAST_DAY;
    }

    private static boolean isSmallerThanUnitDate(final int date) {
        return date < FIRST_DAY;
    }
}
