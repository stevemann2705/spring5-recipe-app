package in.stevemann.spring5recipeapp.services;

import in.stevemann.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}