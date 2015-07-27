package unittests.helpers;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateFactory {

    public static Date date(int day, int month, int year) {
        return new GregorianCalendar(year, month - 1, day).getTime();
    }
}
