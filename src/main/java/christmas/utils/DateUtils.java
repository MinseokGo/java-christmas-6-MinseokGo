package christmas.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;
    public static final int CHRISTMAS_DAY = 25;
    public static final int THIS_YEAR = 2023;
    public static final int THIS_MONTH = 12;
    public static final int EVENT_START_DATE = 1;
    public static final int EVENT_END_DATE = 31;


    public static int getDayNumber(final int visitDate) {
        final LocalDate date = LocalDate.of(DateUtils.THIS_YEAR, THIS_MONTH, visitDate);
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }
}