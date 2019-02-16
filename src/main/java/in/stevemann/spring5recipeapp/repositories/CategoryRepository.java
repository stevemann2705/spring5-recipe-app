package in.stevemann.spring5recipeapp.repositories;

import in.stevemann.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>{
}
