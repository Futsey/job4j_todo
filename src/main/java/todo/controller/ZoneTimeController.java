package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.Task;
import todo.model.User;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

import static todo.util.DateUtil.*;
import static todo.util.HttpSessionUtil.setGuest;

@Controller
@AllArgsConstructor
@RequestMapping("/time")
public class ZoneTimeController {

    @GetMapping()
    public String menu(Model model, HttpSession session) {
        setGuest(model, session);
        return "time/timeZoneAction";
    }

    @GetMapping("/timeZoneAction")
    public String timeZoneAction(Model model, HttpSession session) {
        setGuest(model, session);
        return "time/timeZoneAction";
    }

    @GetMapping("/viewZoneList")
    public String viewZoneList(Model model, HttpSession session) {
        setGuest(model, session);
        model.addAttribute("timezones", showTimeZoneList());
        return "time/viewZoneList";
    }

    @GetMapping("/showLocalTZ")
    public String showLocalTime(Model model, HttpSession session) {
        setGuest(model, session);
        String utcTimeZone = showUTCTZ();
        String timeZone = showLocalTZ();
        model.addAttribute("UTC", utcTimeZone);
        model.addAttribute("timeZone", timeZone);
        return "time/showLocalTZ";
    }

    @GetMapping("/addTZ")
    public String addTZ(User user, Model model, HttpSession session) {
        setGuest(model, session);
        return "time/addTZ";
    }

    @PostMapping("/showLocalPlusOneTZ")
    public String showTwoZones(@ModelAttribute User user, Model model, @RequestParam(name = "zone") String zone, HttpSession session) {
        setGuest(model, session);
        String[] twoZones = showLocalPlusOneTZ(zone);
        model.addAttribute("currentTimeZone", twoZones[0]);
        model.addAttribute("selectedTimeZone", twoZones[1]);
        String timeZone = showUTCTZ();
        model.addAttribute("UTC", timeZone);
        return "time/showLocalPlusOneTZ";
    }


}
