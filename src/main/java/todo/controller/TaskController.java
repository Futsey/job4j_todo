package todo.controller;

import jakarta.servlet.http.HttpSession;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static todo.util.HttpSessionUtil.setGuest;

@ThreadSafe
@Controller
public class TaskController {

    @GetMapping("/taskList")
    public String taskList(Model model, HttpSession session) {
        setGuest(model, session);
        return "taskList";
    }

    @GetMapping("/newTask")
    public String newTask(Model model, HttpSession session) {
        setGuest(model, session);
        return "newTask";
    }
}
