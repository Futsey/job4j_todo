package todo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import todo.model.Priority;
import todo.model.Task;
import todo.service.PriorityService;

import java.util.List;

public class ToDoRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            /** Added priorities. Alter option to add is
             * @see {@link PriorityService#isUrgently()}
            Session session = sf.openSession();
            var priority1 = Priority.builder()
                    .name("urgently")
                    .position(1)
                    .build();
            var priority2 = Priority.builder()
                    .name("normal")
                    .position(2)
                    .build();
            session.saveOrUpdate(priority1);
            session.saveOrUpdate(priority2);
            session.close();
             */
            var stored = listOf("FROM Task f JOIN FETCH f.priority", Task.class, sf);
            for (Task task : stored) {
                System.out.println(task.getPriority());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> List<T> listOf(String query, Class<T> model, SessionFactory sf) {
        Session session = sf.openSession();
        var rsl = session.createQuery(query, model)
                .getResultList();
        session.close();
        return rsl;
    }
}
