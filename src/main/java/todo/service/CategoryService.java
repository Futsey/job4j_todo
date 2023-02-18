package todo.service;

import org.springframework.stereotype.Service;
import todo.model.Category;
import todo.store.CategoryDBStore;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryDBStore store;

    public CategoryService(CategoryDBStore store) {
        this.store = store;
    }

    public List<Category> findAll() {
        return store.findAll();
    }

    public Optional<Category> findById(int id) {
        return store.findById(id);
    }

    public List<Category> findAllById(List<Long> categoryIdList) {
        return store.findAllById(categoryIdList);
    }
}
