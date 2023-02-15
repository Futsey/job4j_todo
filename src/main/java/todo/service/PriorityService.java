package todo.service;

import org.springframework.stereotype.Service;
import todo.model.Priority;
import todo.model.Task;
import todo.store.PriorityDBStore;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {

    private final PriorityDBStore store;

    public PriorityService(PriorityDBStore store) {
        this.store = store;
    }

    public Optional<Priority> add(Priority priority) {
        return store.add(priority);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public boolean update(Priority priority) {
        return store.update(priority);
    }

    public List<Priority> findAll() {
        return store.findAll();
    }

    public boolean isUrgently() {
        return store.isUrgently();
    }

    public Optional<Priority> findById(int id) {
        return store.findById(id);
    }

    public boolean isPriorityPresent(int id) {
        return  store.isPriorityPresent(id);
    }
}
