package todo.util;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import todo.store.CRUDStore;
import todo.store.UserDBStore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public final class DateUtil {

    private DateUtil() {
    }

    public static LocalDateTime convertToDate(String timeZone) {
        String tmp = showSelectedTZ(timeZone);
        return ZonedDateTime.now(ZoneId.of(tmp)).toLocalDateTime();
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

    public static ZoneId getZoneID(String timeZone) {
        String result = timeZone.split(":")[0];
        TimeZone userTZ = TimeZone.getTimeZone(result.trim());
        return userTZ.toZoneId();
    }

    public static String showSelectedTZ(String timeZone) {
        String result = timeZone.split(":")[0];
        Date today = new Date();
        TimeZone userTZ = TimeZone.getTimeZone(result.trim());
        DateFormat df = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        df.setTimeZone(userTZ);
        String tmpDate = df.format(today);

        StringBuilder stringBuilder = new StringBuilder("Current TimeZone : \"");
        stringBuilder.append(userTZ.getID());
        stringBuilder.append("\" (");
        stringBuilder.append(userTZ.getDisplayName());
        stringBuilder.append(") Time\\Date: ");
        stringBuilder.append(tmpDate);

        return userTZ.getID();
    }

    public static String[] showLocalPlusOneTZ(String timeZone) {
        String[] tzArray = new String[2];
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        TimeZone upperTZ = TimeZone.getDefault();
        TimeZone lowerTZ = TimeZone.getTimeZone(timeZone);

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
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
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
        List<String> zoneList = new ArrayList<>();
        for (String timeId : TimeZone.getAvailableIDs()) {

            zones.add(TimeZone.getTimeZone(timeId));
        }
        for (TimeZone zone : zones) {
            StringBuilder tmp = new StringBuilder(zone.getID());
            tmp.append(" : ");
            tmp.append(zone.getDisplayName());
            zoneList.add(String.valueOf(tmp));
            tmp.delete(0, 2);
        }
        return zoneList;
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

    public static void main(String[] args) {
        var registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (var sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var session = sf.openSession();
            session.beginTransaction();
            System.out.println("------------------------------");

            System.out.println("------------------------------");
        }
    }
}
