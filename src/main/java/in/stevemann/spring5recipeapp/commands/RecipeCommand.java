package in.stevemann.spring5recipeapp.commands;

import in.stevemann.spring5recipeapp.domain.Category;
import in.stevemann.spring5recipeapp.domain.Difficulty;
import in.stevemann.spring5recipeapp.domain.Ingredient;
import in.stevemann.spring5recipeapp.domain.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Notes notes;
    private Difficulty difficulty;
    private Set<Category> categories = new HashSet<>();
}
