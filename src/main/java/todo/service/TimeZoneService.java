package todo.service;

import org.springframework.stereotype.Service;
import todo.model.Task;
import todo.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import static todo.util.DateUtil.*;

@Service
public class TimeZoneService {

    public List<TimeZone> findAllTZ() {
        return showTimeZoneList();
    }

    public String findSelectedTZ(String timeZone) {
        return getTZ(timeZone);
    }

    public LocalDateTime selectTZ(User user, Task task) {
        return setSelectedTZ(user, task);
    }
}
