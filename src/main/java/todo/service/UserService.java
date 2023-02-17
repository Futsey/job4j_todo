package todo.service;

import org.springframework.stereotype.Service;
import todo.model.User;
import todo.store.UserDBStore;

import java.util.List;
import java.util.Optional;

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

    public boolean update(int id) {
        return store.update(id);
    }

    public List<User> findAll() {
        return store.findAll();
    }

    public Optional<User> findById(int id) {
        return store.findById(id);
    }

    public Optional<User> findByLogin(String name) {
        return store.findByLogin(name);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }
}
