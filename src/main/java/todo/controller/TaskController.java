package todo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import todo.model.Task;
import todo.service.TaskService;

import java.io.IOException;

import static todo.util.HttpSessionUtil.setGuest;

@ThreadSafe
@Controller
@AllArgsConstructor

public class TaskController {

    private final TaskService taskService;

    @GetMapping("/taskList")
    public String taskList(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAll());
        setGuest(model, session);
        return "taskList";
    }

    @GetMapping("/formTaskInfo/{taskId}")
    public String formTaskInfo(Model model, @PathVariable("taskId") int id, HttpSession session) {
        model.addAttribute("task", taskService.findById(id));
        setGuest(model, session);
        return "taskInfo";
    }

    @PostMapping("/taskInfo")
    public String taskInfo(@ModelAttribute Task task) {
        taskService.findById(task.getId());
        return "redirect:/taskList";
    }

    @GetMapping("/formTaskInfoDone")
    public String formTaskInfoDone(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findCompletedTasks());
        setGuest(model, session);
        return "taskInfoDone";
    }

    @GetMapping("/formTaskInfoUnDone")
    public String formTaskInfoUnDone(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findUncompletedTasks());
        setGuest(model, session);
        return "taskInfoUnDone";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id, HttpSession session) {
        model.addAttribute("tasks", taskService.findById(id));
        setGuest(model, session);
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/taskList";
    }

    @GetMapping("/formAddTask")
    public String addTask(Model model, HttpSession session) {
        model.addAttribute("task",
                new Task());
        setGuest(model, session);
        return "addTask";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        taskService.add(task);
        return "redirect:/taskList";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable("taskId") int id) {
        taskService.delete(id);
        return "redirect:/taskList";
    }
}
