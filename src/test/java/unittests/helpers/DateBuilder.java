package unittests.helpers;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateBuilder {
    public static Date aDate(int year, int month, int day, int minute, int second) {
        return new GregorianCalendar(year, month, day, minute, second).getTime();
    }

    public static Date date(int day, int month, int year) {
        return new GregorianCalendar(year, month - 1, day).getTime();
    }
}
