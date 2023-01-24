package todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import todo.model.User;
import todo.store.UserDBStore;

import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class UserService {

    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public boolean update(int id, User user) {
        return store.update(id, user);
    }

    public List<User> findAll() {
        return store.findAll();
    }

    public User findById(int id) {
        return store.findById(id);
    }

    public Optional<User> findByName(String name) {
        return store.findByLogin(name);
    }
}
