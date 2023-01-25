package todo.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.Task;
import todo.service.TaskService;

import javax.servlet.http.HttpSession;

import static todo.util.HttpSessionUtil.setGuest;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public String list(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.findAll());
        setGuest(model, session);
        return "tasks/list";
    }

    @GetMapping("/info/{id}")
    public String formTaskInfo(Model model, @PathVariable("id") int id, HttpSession session) {
        model.addAttribute("task", taskService.findById(id));
        setGuest(model, session);
        return "tasks/info";
    }

    @GetMapping("/done")
    public String formTaskInfoDone(Model model, @ModelAttribute Task task, HttpSession session) {
        model.addAttribute("tasks", taskService.sortTasks(true));
        setGuest(model, session);
        return "tasks/done";
    }

    @GetMapping("/unDone")
    public String formTaskInfoUnDone(Model model, HttpSession session) {
        model.addAttribute("tasks", taskService.sortTasks(false));
        setGuest(model, session);
        return "tasks/unDone";
    }

    @GetMapping("/update/{id}")
    public String formUpdateTask(Model model, @PathVariable("id") int id, HttpSession session) {
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

    @GetMapping("/execution/{id}")
    public String editTaskExecution(@PathVariable("id") int id) {
        String rsl = "redirect:/tasks";
        if (!taskService.isDone(id)) {
            rsl = "redirect:/executionFail";
        }
        return rsl;
    }

    @GetMapping("/add")
    public String addTask(Model model, HttpSession session) {
        model.addAttribute("task",
                new Task());
        setGuest(model, session);
        return "tasks/add";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
            taskService.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") int id) {
        String rsl = "redirect:/tasks";
        if (!taskService.delete(id)) {
            rsl = "redirect:/deleteFail";
        }
        return rsl;
    }
}
