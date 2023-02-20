package todo.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import todo.model.Task;
import todo.service.CategoryService;
import todo.service.PriorityService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskDBStore {

    private final CRUDStore crudRepository;
    private final PriorityService priorityService;
    private final CategoryService categoryService;
    private static final Logger LOG = LoggerFactory.getLogger(TaskDBStore.class.getName());

    private static final String SELECT_ALL_WITH_PRIORITY_AND_CATEGORY = """
            SELECT distinct t FROM Task t 
            JOIN FETCH t.priority
            JOIN FETCH t.categoryList
            """;
    private static final String SELECT_BY_ID = """
            FROM Task t 
            JOIN FETCH t.priority 
            JOIN FETCH t.categoryList
            WHERE t.id = :fId
            """;
    private static final String SELECT_COMPLETED = """
            FROM Task t
            WHERE t.done = :fdone
            """;
    private static final String UPDATE = """
            UPDATE Task t
            SET t.description = :fdescription, t.done = :fdone, t.priority = :fpriority, t.user = :fuser, t.categoryList = :fcategoryList
            WHERE t.id = :fId
            """;
    private static final String UPDATE_DONE_STATE = """
            UPDATE Task t
            SET t.done = true
            WHERE t.id = :fId
            """;
    private static final String DELETE = """
            DELETE Task t
            WHERE t.id = :fId
            """;

    public TaskDBStore(CRUDStore crudRepository, PriorityService priorityService, CategoryService categoryService) {
        this.crudRepository = crudRepository;
        this.priorityService = priorityService;
        this.categoryService = categoryService;
    }

    public boolean add(Task task) {
        boolean rsl = false;
        if (isPriorityPresent(task)) {
            crudRepository.run(session -> session.save(task));
            rsl = true;
        }
        return rsl;
    }

    public boolean isPriorityPresent(Task task) {
        return priorityService.findById(task.getPriority().getId()).isPresent();
    }

    public List<Task> findAll() {
        return crudRepository.query(SELECT_ALL_WITH_PRIORITY_AND_CATEGORY, Task.class);
    }

    public List<Task> sortTasks(boolean done) {
        return crudRepository.query(
                SELECT_COMPLETED, Task.class,
                Map.of("fdone", done));
    }

    public boolean update(Task task) {
        boolean rsl = false;
        if (isPriorityPresent(task)) {
            crudRepository.run(UPDATE, Map.of(
                    "fdescription", task.getDescription(),
                    "fdone", task.isDone(),
                    "fpriority", task.getPriority(),
                    "fuser", task.getUser(),
                    "fcategoryList", task.getCategoryList(),
                    "fId", task.getId()));
            rsl = true;
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
