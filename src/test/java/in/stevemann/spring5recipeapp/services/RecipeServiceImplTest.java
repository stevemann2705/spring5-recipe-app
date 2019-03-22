package in.stevemann.spring5recipeapp.services;

import in.stevemann.spring5recipeapp.converters.RecipeCommandToRecipe;
import in.stevemann.spring5recipeapp.converters.RecipeToRecipeCommand;
import in.stevemann.spring5recipeapp.domain.Recipe;
import in.stevemann.spring5recipeapp.exceptions.NotFoundException;
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

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
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

    @Test
    public void deleteById() {
        recipeService.deleteById(Long.valueOf(2L));

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //should go boom
    }
}