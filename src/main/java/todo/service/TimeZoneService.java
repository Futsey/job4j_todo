package todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static todo.util.DateUtil.*;

@Service
public class TimeZoneService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class.getName());

    public List<String> findAllTZ() {
        return showTimeZoneList();
    }

    public LocalDateTime findSelectedTZ(String timeZone) {
        return convertToDate(timeZone);
    }
}
