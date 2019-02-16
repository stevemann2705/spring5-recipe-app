package in.stevemann.spring5recipeapp.repositories;

import in.stevemann.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
