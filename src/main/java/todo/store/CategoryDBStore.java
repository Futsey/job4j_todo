package todo.store;

import org.springframework.stereotype.Repository;
import todo.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryDBStore {

    private final CRUDStore crudRepository;
    private static final String SELECT_ALL = """
            FROM Category""";
    private static final String SELECT_BY_ID = """
            FROM Category 
            WHERE id = :fId""";
    private static final String SELECT_BY_SEVERAL_ID = """
            FROM Category 
            WHERE id 
            IN (:fIdList)""";

    public CategoryDBStore(CRUDStore crudRepository) {
        this.crudRepository = crudRepository;
    }

    public List<Category> findAll() {
        return crudRepository.query(SELECT_ALL, Category.class);
    }

    public Optional<Category> findById(int id) {
        return crudRepository.optional(
                SELECT_BY_ID, Category.class,
                Map.of("fId", id)
        );
    }

    public List<Category> findAllById(List<Long> categoryIdList) {
        return crudRepository.query(
                SELECT_BY_SEVERAL_ID, Category.class,
                Map.of("fIdList", categoryIdList));
    }
}
