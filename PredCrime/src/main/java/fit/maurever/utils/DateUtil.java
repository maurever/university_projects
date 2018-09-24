package fit.maurever.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Util, specific format of date.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class DateUtil {

    public static String getDateTime_ddMMyyyy_HHmm() {
        DateFormat dfFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
        return dfFormat.format(new Date());
    }

    public static String getDateTime_ddMMyyyy() {
        DateFormat dfFormat = new SimpleDateFormat("ddMMyyyy");
        return dfFormat.format(new Date());
    }

    public static String getDateTime_ddMMyyyy_withSpaces() {
        DateFormat dfFormat = new SimpleDateFormat("dd MM yyyy");
        return dfFormat.format(new Date());
    }

    public static String getDateTime_ddMMyyyy_withSpaces(Date date) {
        DateFormat dfFormat = new SimpleDateFormat("dd MM yyyy");
        return dfFormat.format(date);
    }
}
