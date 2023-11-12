package christmas.util;

import java.util.Calendar;

public class DateUtil {
    private static final int year = 2023;
    private static boolean isWeekday(int day) { // 평일이면 true
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.DECEMBER, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 1은 일요일, 2는 월요일, ..., 7은 토요일을 나타냅니다.
        return dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY;
    }

    public static int isWeekend(int day) {
        if (!isWeekday(day)) //주말이면 1
            return 1;
        return 0;
    }

    public static boolean isSpecialday(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.DECEMBER, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek == Calendar.SUNDAY || day == 25;
    }

}
