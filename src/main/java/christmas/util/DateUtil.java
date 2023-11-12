package christmas.util;

import java.util.Calendar;

public class DateUtil {
    private static final int year = 2023;
    private static final int month = 12;
    public static boolean isWeekday(int day) { // 평일이면 true
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 1은 일요일, 2는 월요일, ..., 7은 토요일을 나타냅니다.
        return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY;
    }

//    public static boolean isWeekend(int day) {
//        return !isWeekday(day);
//    }
}
