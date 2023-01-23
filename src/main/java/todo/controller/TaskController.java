package todo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.Task;
import todo.service.TaskService;

import java.util.Optional;

import static todo.util.HttpSessionUtil.setGuest;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("")
    public String list(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAll());
        setGuest(model, session);
        return "tasks/list";
    }

    @GetMapping("/formInfo/{taskId}")
    public String formTaskInfo(Model model, @PathVariable("taskId") int id, HttpSession session) {
        model.addAttribute("task", taskService.findById(id));
        setGuest(model, session);
        return "tasks/info";
    }

    @GetMapping("/formInfoDone")
    public String formTaskInfoDone(Model model, @ModelAttribute Task task, HttpSession session) {
        model.addAttribute("tasks", taskService.sortTasks(true));
        setGuest(model, session);
        return "tasks/infoDone";
    }

    @GetMapping("/formInfoUnDone")
    public String formTaskInfoUnDone(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.sortTasks(false));
        setGuest(model, session);
        return "tasks/infoUnDone";
    }

    @GetMapping("/formUpdate/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id, HttpSession session) {
        model.addAttribute("tasks", taskService.findById(id));
        setGuest(model, session);
        return "tasks/edit";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute Task task) {
        String rsl = "redirect:/tasks";
        if (!taskService.update(task)) {
            rsl = "redirect:/tasks/editFail";
        }
        return rsl;
    }

    @GetMapping("/execution/{taskId}")
    public String editTaskExecution(@PathVariable("taskId") int id) {
        String rsl = "redirect:/tasks";
        if (!taskService.isDone(id)) {
            rsl = "redirect:/executionFail";
        }
        return rsl;
    }

    @GetMapping("/formAdd")
    public String addTask(Model model, HttpSession session) {
        model.addAttribute("task",
                new Task());
        setGuest(model, session);
        return "tasks/add";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        Optional<Task> nonNullTask = Optional.ofNullable(task);
        String rsl;
        if (nonNullTask.isEmpty()) {
            rsl = "redirect:/createFail";
        } else {
            taskService.add(task);
            rsl = "redirect:/tasks";
        }
        return rsl;
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") int id) {
        String rsl = "redirect:/tasks";
        if (!taskService.delete(id)) {
            rsl = "redirect:/deleteFail";
        }
        return rsl;
    }
}
