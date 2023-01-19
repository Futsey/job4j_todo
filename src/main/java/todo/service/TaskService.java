package todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import todo.model.Task;
import todo.store.TaskDBStore;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class TaskService {

    private final TaskDBStore store;

    public TaskService(TaskDBStore store) {
        this.store = store;
    }

    public Optional<Task> add(Task task) {
        return store.add(task);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public void update(Task task) {
        store.update(task);
    }

    public List<Task> findAll() {
        return store.findAll();
    }

    public List<Task> findCompletedTasks() {
        return store.findCompletedTasks();
    }

    public List<Task> findUncompletedTasks() {
        return store.findUncompletedTasks();
    }

    public Task findById(int id) {
        return store.findById(id);
    }
}
