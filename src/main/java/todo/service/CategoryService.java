package todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import todo.model.Category;
import todo.store.CategoryDBStore;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryDBStore store;
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class.getName());

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
        List<Category> categoryList = store.findAllById(categoryIdList);
        if (categoryIdList.size() != categoryList.size()) {
            LOG.error("List size mismatch: CategoryService{ findAllById() }");
        }
        return categoryList;
    }
}
