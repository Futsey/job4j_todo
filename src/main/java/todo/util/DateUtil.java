package todo.util;

import todo.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public final class DateUtil {

    private DateUtil() {
    }

    public static String showLocalTZ() {
        Date today = new Date();
        TimeZone userTZ = TimeZone.getDefault();
        DateFormat df = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        df.setTimeZone(userTZ);
        String tmpDate = df.format(today);

        StringBuilder stringBuilder = new StringBuilder("Current TimeZone : \"");
        stringBuilder.append(userTZ.getID());
        stringBuilder.append("\" (");
        stringBuilder.append(userTZ.getDisplayName());
        stringBuilder.append(") Time\\Date: ");
        stringBuilder.append(tmpDate);

        return String.valueOf(stringBuilder);
    }

    public static String[] showLocalPlusOneTZ(String zone) {
        String[] tzArray = new String[2];
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        TimeZone upperTZ = TimeZone.getDefault();
        TimeZone lowerTZ = TimeZone.getTimeZone(zone);

        df.setTimeZone(upperTZ);
        String localDate = df.format(today);

        StringBuilder stringBuilder = new StringBuilder("Current TimeZone : \"");
        stringBuilder.append(upperTZ.getID());
        stringBuilder.append("\" (");
        stringBuilder.append(upperTZ.getDisplayName());
        stringBuilder.append(") Time\\Date: ");
        stringBuilder.append(localDate);
        tzArray[0] = String.valueOf(stringBuilder);

        stringBuilder.setLength(0);
        df.setTimeZone(TimeZone.getTimeZone(zone));
        String tmpDate = df.format(today);

        stringBuilder.append(lowerTZ.getID());
        stringBuilder.append("\" (");
        stringBuilder.append(lowerTZ.getDisplayName());
        stringBuilder.append(") Time\\Date: ");
        stringBuilder.append(tmpDate);
        tzArray[1] = String.valueOf(stringBuilder);

        return tzArray;
    }

    public static List<String> showTimeZoneList() {
        var zones = new ArrayList<TimeZone>();
        List<String> strZones = new ArrayList<>();
        for (String timeId : TimeZone.getAvailableIDs()) {

            zones.add(TimeZone.getTimeZone(timeId));
        }
        for (TimeZone zone : zones) {
            StringBuilder tmp = new StringBuilder(zone.getID());
            tmp.append(" : ");
            tmp.append(zone.getDisplayName());
            strZones.add(String.valueOf(tmp));
            tmp.delete(0, 2);
        }
        return strZones;
    }

    public static String showUTCTZ() {
        Date today = new Date();
        TimeZone userTZ = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        df.setTimeZone(userTZ);
        String tmpDate = df.format(today);

        StringBuilder stringBuilder = new StringBuilder("Current TimeZone : \"");
        stringBuilder.append(userTZ.getID());
        stringBuilder.append("\" (");
        stringBuilder.append(userTZ.getDisplayName());
        stringBuilder.append(") Time\\Date: ");
        stringBuilder.append(tmpDate);

        return String.valueOf(stringBuilder);
    }
}
