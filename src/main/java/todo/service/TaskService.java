package todo.service;

import org.springframework.stereotype.Service;
import todo.model.Task;
import todo.store.TaskDBStore;

import java.util.List;
import java.util.Optional;

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

    public boolean update(Task task) {
        return store.update(task);
    }

    public boolean isDone(int id) {
        return store.isDone(id);
    }

    public List<Task> findAll() {
        return store.findAll();
    }

    public List<Task> sortTasks(boolean done) {
        return store.sortTasks(done);
    }

    public Task findById(int id) {
        return store.findById(id);
    }
}
