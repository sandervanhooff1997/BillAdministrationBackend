package domain.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    public static String getMonthName(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int month = c.get(Calendar.MONTH);
        return String.valueOf( month );
    }

    public static int getMonthIndex(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.MONTH);
    }
}