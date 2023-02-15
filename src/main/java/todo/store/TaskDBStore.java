package todo.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskDBStore {

    private final CRUDStore crudRepository;
    private static final Logger LOG = LoggerFactory.getLogger(TaskDBStore.class.getName());
    private static final String SELECT_ALL = "FROM Task f JOIN FETCH f.priority";
    private static final String SELECT_BY_ID = "FROM Task f JOIN FETCH f.priority WHERE f.id = :fId";
    private static final String SELECT_COMPLETED = "FROM Task WHERE done = :fdone";
    private static final String UPDATE = "UPDATE Task SET description = :fdescription, done = :fdone, "
            + "priority = :fpriority WHERE id = :fId";
    private static final String UPDATE_DONE_STATE = "UPDATE Task SET done = true WHERE id = :fId";
    private static final String DELETE = "DELETE Task WHERE id = :fId";

    public TaskDBStore(CRUDStore crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Optional<Task> add(Task task) {
        Optional<Task> nonNullTask = Optional.empty();
        try {
            crudRepository.run(session -> session.save(task));
            nonNullTask = Optional.of(task);
        } catch (Exception e) {
            LOG.error("Exception: TaskDBStore{ add() }", e);
        }
        return nonNullTask;
    }

    public List<Task> findAll() {
        return crudRepository.query(SELECT_ALL, Task.class);
    }

    public List<Task> sortTasks(boolean done) {
        return crudRepository.query(
                SELECT_COMPLETED, Task.class,
                Map.of("fdone", done));
    }

    public boolean update(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(UPDATE, Map.of(
                    "fdescription", task.getDescription(),
                    "fdone", task.isDone(),
                    "fpriority", task.getPriority(),
                    "fId", task.getId()));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: TaskDBStore{ update() }", e);
        }
        return rsl;
    }

    public boolean isDone(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(UPDATE_DONE_STATE, Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: TaskDBStore{ update() }", e);
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: TaskDBStore{ update() }", e);
        }
        return rsl;
    }

    public Optional<Task> findById(int id) {
        return crudRepository.optional(
                SELECT_BY_ID, Task.class,
                Map.of("fId", id)
        );
    }
}
