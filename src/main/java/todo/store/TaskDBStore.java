package todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import todo.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskDBStore {

    private final SessionFactory sf;
    private static final String SELECT_ALL = "FROM Task";
    private static final String SELECT_COMPLETED = "FROM Task WHERE done IS TRUE";
    private static final String SELECT_UNCOMPLETED = "FROM Task WHERE done IS NOT TRUE";
    private static final String UPDATE = "UPDATE Task SET description = :fdescription, done = :fdone WHERE id = :fId";
    private static final String DELETE = "DELETE Task WHERE id = :fId";

    public TaskDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<Task> add(Task task) {
        Optional<Task> notNulltask = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            notNulltask = Optional.of(task);
            System.out.println(notNulltask);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return notNulltask;
    }

    public List<Task> findAll() {
        Session session = sf.openSession();
        Query<Task> query = session.createQuery(SELECT_ALL, Task.class);
        List<Task> allTaskList = query.list();
        session.close();
        return allTaskList;
    }

    public List<Task> findCompletedTasks() {
        Session session = sf.openSession();
        Query<Task> query = session.createQuery(SELECT_COMPLETED, Task.class);
        List<Task> allTaskList = query.list();
        session.close();
        return allTaskList;
    }

    public List<Task> findUncompletedTasks() {
        Session session = sf.openSession();
        Query<Task> query = session.createQuery(SELECT_UNCOMPLETED, Task.class);
        List<Task> allTaskList = query.list();
        session.close();
        return allTaskList;
    }

    public boolean update(Task task) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(UPDATE)
                    .setParameter("fdescription", task.getDescription())
                    .setParameter("fdone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate() != 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(DELETE)
                    .setParameter("fId", id)
                    .executeUpdate() != 0;
            session.getTransaction().commit();
            System.out.println("DELETED");
        } catch (Exception e) {
            System.out.println("ХРЕН!!!!!");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    public Task findById(int id) {
        Session session = sf.openSession();
        Task item = session.find(Task.class, id);
        session.close();
        return item;
    }
}
