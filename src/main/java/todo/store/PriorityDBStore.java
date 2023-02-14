package todo.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import todo.model.Priority;
import todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PriorityDBStore {

    private final CRUDStore crudRepository;
    private static final Logger LOG = LoggerFactory.getLogger(TaskDBStore.class.getName());
    private static final String SELECT_ALL = "FROM Priority";
    private static final String UPDATE = "UPDATE Priority SET name = :fname, position = :fposition WHERE id = :fId";
    private static final String SORT_URGENTLY =
            "UPDATE tasks SET priority_id = (SELECT id FROM priorities WHERE name = :furgently)";
    private static final String DELETE = "DELETE Priority WHERE id = :fId";
    private static final String SELECT_BY_ID = "FROM Priority WHERE id = :fId";

    public PriorityDBStore(CRUDStore crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Optional<Priority> add(Priority priority) {
        Optional<Priority> nonNullPriority = Optional.empty();
        try {
            crudRepository.run(session -> session.save(priority));
            nonNullPriority = Optional.of(priority);
        } catch (Exception e) {
            LOG.error("Exception: PriorityDBStore{ add() }", e);
        }
        return nonNullPriority;
    }

    public List<Priority> findAll() {
        return crudRepository.query(SELECT_ALL, Priority.class);
    }

    public boolean update(Priority priority) {
        boolean rsl = false;
        try {
            crudRepository.run(UPDATE, Map.of(
                    "fdescription", priority.getName(),
                    "fdone", priority.getPosition(),
                    "fId", priority.getId()));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: PriorityDBStore{ update() }", e);
        }
        return rsl;
    }

    public boolean isUrgently() {
        boolean rsl = false;
        try {
            crudRepository.run(SORT_URGENTLY, Map.of("furgently", "urgently"));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: PriorityDBStore{ isUrgently() }", e);
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: PriorityDBStore{ delete() }", e);
        }
        return rsl;
    }

    public Optional<Priority> findById(int id) {
        return crudRepository.optional(
                SELECT_BY_ID, Priority.class,
                Map.of("fId", id)
        );
    }
}
