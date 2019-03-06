package in.stevemann.spring5recipeapp.services;

import in.stevemann.spring5recipeapp.domain.Recipe;
import in.stevemann.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> returnedRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(returnedRecipe);

        Recipe recipeGot = recipeService.findById(1L);

        assertNotNull(recipeGot);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipes = new HashSet();
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        Set<Recipe> recipeSet = recipeService.getRecipes();

        assertEquals(recipeSet.size(), recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}