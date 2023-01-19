package todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import todo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDBStore {

    private final SessionFactory sf;
    private static final String SELECT_ALL = "FROM User";
    private static final String SELECT_WHERE_NAME = "FROM User WHERE name = :key";
    private static final String UPDATE = "UPDATE User SET name = :fname, email = :femail, "
            + "password = :fpassword WHERE id = :fId";
    private static final String DELETE = "DELETE User WHERE id = :fId";

    public UserDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> add(User user) {
        Optional<User> notNullUser = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            notNullUser = Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return notNullUser;
    }

    public List<User> findAll() {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(SELECT_ALL, User.class);
        session.close();
        return query.list();
    }

    public boolean update(int id, User user) {
        boolean rsl = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            rsl = session.createQuery(UPDATE)
                    .setParameter("fname", user.getName())
                    .setParameter("femail", user.getEmail())
                    .setParameter("fpassword", user.getPassword())
                    .setParameter("fId", id)
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
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    public Optional<User> findByName(String key) {
        Session session = sf.openSession();
        Optional<User> notNullUser = Optional.empty();
        List<User> itemList = session.createQuery(SELECT_WHERE_NAME, User.class)
                .setParameter("key", key)
                .list();
        if (itemList.size() == 0) {
            return notNullUser;
        }
        notNullUser = Optional.ofNullable(itemList.get(0));
        session.close();
        return notNullUser;
    }

    public User findById(int id) {
        Session session = sf.openSession();
        User item = session.find(User.class, id);
        session.close();
        return item;
    }
}
