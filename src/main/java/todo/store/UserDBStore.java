package todo.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import todo.model.User;

import java.util.*;

@Repository
public class UserDBStore {

    private final CRUDStore crudRepository;
    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class.getName());
    private static final String SELECT_ALL = "FROM User";
    private static final String SELECT_BY_ID = "FROM Task WHERE id = :fId";
    private static final String SELECT_WHERE_LOGIN = "FROM User WHERE login = :fkey";
    private static final String SELECT_WHERE_LOGIN_AND_PASSWORD =
            "FROM User WHERE login = :flogin AND password = :fpassword";
    private static final String UPDATE = "UPDATE User SET name = :fname, email = :femail, "
            + "password = :fpassword WHERE id = :fId";
    private static final String DELETE = "DELETE User WHERE id = :fId";

    public UserDBStore(CRUDStore crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Optional<User> add(User user) {
        Optional<User> nonNullUser = Optional.empty();
        try {
            crudRepository.run(session -> session.save(user));
            nonNullUser = Optional.of(user);
        } catch (Exception e) {
            LOG.error("Exception: UserDBStore{ add() }", e);
        }
        return nonNullUser;
    }

    public List<User> findAll() {
        return crudRepository.query(SELECT_ALL, User.class);
    }

    public boolean update(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(UPDATE, Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: UserDBStore{ update() }", e);
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Exception: UserDBStore{ update() }", e);
        }
        return rsl;
    }

    public Optional<User> findByLogin(String key) {
        return crudRepository.optional(
                SELECT_WHERE_LOGIN, User.class,
                Map.of("fkey", key)
        );
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        Optional<User> userDB = Optional.empty();
        try {
            userDB = crudRepository.optional(
                    SELECT_WHERE_LOGIN_AND_PASSWORD,
                    User.class,
                    Map.of("flogin", login, "fpassword", password));
        } catch (Exception e) {
            LOG.error("Exception: UserDBStore{ findByLoginAnaPassword() }", e);
        }
        return userDB;
    }

    public Optional<User> findById(int id) {
        return crudRepository.optional(
                SELECT_BY_ID, User.class,
                Map.of("fId", id)
        );
    }
}
