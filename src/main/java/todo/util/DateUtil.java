package todo.util;

import todo.model.Task;
import todo.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public final class DateUtil {

    private static final String PATTERN = "HH:mm yyyy-MM-dd";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);

    private DateUtil() {
    }

    public static String getTZ(String timeZone) {
        return TimeZone.getTimeZone(timeZone).getID();
    }

    public static LocalDateTime setSelectedTZ(User user, Task task) {
        ZonedDateTime zonedDateTime;
            if (user.getUserZone() == null) {
                zonedDateTime = ZonedDateTime.of(task.getCreated(), TimeZone.getDefault().toZoneId());
            } else {
                zonedDateTime = ZonedDateTime.of(task.getCreated(),
                                TimeZone.getDefault().toZoneId()).withZoneSameInstant(ZoneId.of(String.valueOf(user.getUserZone())));
            }
            return zonedDateTime.toLocalDateTime();
    }

    public static String showLocalTZ() {
        String[] tzArgs = setTZArgs(TimeZone.getDefault().getID());
        return buildSBForm(tzArgs[0], tzArgs[1], tzArgs[2]);
    }

    public static String[] showLocalPlusOneTZ(String timeZone) {
        String[] tzArray = new String[2];
        String[] tzArgs = setTZArgs(TimeZone.getDefault().getID());
        tzArray[0] = buildSBForm(tzArgs[0], tzArgs[1], tzArgs[2]);
        String[] secondTZArgs = setTZArgs(timeZone);
        tzArray[1] = buildSBForm(secondTZArgs[0], secondTZArgs[1], secondTZArgs[2]);
        return tzArray;
    }

    public static List<TimeZone> showTimeZoneList() {
        var zones = new ArrayList<TimeZone>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        return zones;
    }

    public static String showUTCTZ() {
        String[] tzArgs = setTZArgs("UTC");
        return buildSBForm(tzArgs[0], tzArgs[1], tzArgs[2]);
    }

    public static String[] setTZArgs(String timeZone) {
        String[] argsArray = new String[3];
        Date today = new Date();
        TimeZone userTZ = TimeZone.getTimeZone(timeZone);
        DATE_FORMAT.setTimeZone(userTZ);
        String tmpDate = DATE_FORMAT.format(today);
        argsArray[0] = userTZ.getID();
        argsArray[1] = userTZ.getDisplayName();
        argsArray[2] = tmpDate;
        return argsArray;
    }

    public static String buildSBForm(String tzID, String name, String date) {
        StringBuilder stringBuilder = new StringBuilder("Current TimeZone : \"");
        stringBuilder.append(tzID);
        stringBuilder.append("\" (");
        stringBuilder.append(name);
        stringBuilder.append(") Time\\Date: ");
        stringBuilder.append(date);
        return String.valueOf(stringBuilder);
    }
}
