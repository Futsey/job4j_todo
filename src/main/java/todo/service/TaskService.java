package todo.service;

import org.springframework.stereotype.Service;
import todo.model.Task;
import todo.store.TaskDBStore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskDBStore store;
    private final TimeZoneService timeZoneService;

    public TaskService(TaskDBStore store, TimeZoneService timeZoneService) {
        this.store = store;
        this.timeZoneService = timeZoneService;
    }

    public boolean add(Task task) {
        task.setCreated(LocalDateTime.now());
        return store.add(task);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public boolean update(Task task) {
        return store.update(task);
    }

    public boolean isDone(int id) {
        return store.isDone(id);
    }

    public List<Task> findAll() {
        List<Task> taskList = store.findAll();
        for (Task task : taskList) {
            task.setCreated(timeZoneService.selectTZ(task.getUser(), task));
        }
        return taskList;
    }

    public List<Task> sortTasks(boolean done) {
        return store.sortTasks(done);
    }

    public Optional<Task> findById(int id) {
        return store.findById(id);
    }
}
