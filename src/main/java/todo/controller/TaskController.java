package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.Category;
import todo.model.Task;
import todo.model.User;
import todo.service.CategoryService;
import todo.service.PriorityService;
import todo.service.TaskService;
import todo.service.TimeZoneService;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static todo.util.HttpSessionUtil.setGuest;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;
    private final TimeZoneService timeZoneService;

    @GetMapping()
    public String list(Model model, HttpSession session) {
        List<Task> all = taskService.findAll();
        model.addAttribute("tasks", all);
        setGuest(model, session);
        return "tasks/list";
    }

    @GetMapping("/info/{id}")
    public String formTaskInfo(Model model, @PathVariable("id") int id, HttpSession session) {
        Optional<Task> taskDb = taskService.findById(id);
        taskDb.ifPresent(task -> model.addAttribute("task", task));
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
        Optional<Task> taskDb = taskService.findById(id);
        taskDb.ifPresent(task -> model.addAttribute("task", task));
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("timezones", timeZoneService.findAllTZ());
        setGuest(model, session);
        return "tasks/edit";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute Task task,
                           @RequestParam(value = "category", required = false) List<Long> categoryId,
                           HttpSession session) {
        String rsl = "redirect:/tasks";
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        List<Category> catTempList = categoryService.findAllById(categoryId);
        task.setCategoryList(catTempList);
        if (!taskService.update(task)) {
            rsl = "/editFail";
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
        setGuest(model, session);
        model.addAttribute("task", new Task());
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "tasks/add";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task, @RequestParam("category") List<Long> categoryId, HttpSession session) {
        String rsl = "redirect:/tasks";
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        task.setCategoryList(categoryService.findAllById(categoryId));
        if (!taskService.add(task)) {
            rsl = "/createFail";
        }
        return rsl;
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
