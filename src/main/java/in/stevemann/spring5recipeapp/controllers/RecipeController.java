package in.stevemann.spring5recipeapp.controllers;

import in.stevemann.spring5recipeapp.commands.RecipeCommand;
import in.stevemann.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RecipeController {
    public final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        log.debug("Calling showById() method in Recipe Controller");

        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        log.debug("Calling newRecipe() method in Recipe Controller");

        model.addAttribute("recipe", new RecipeCommand());

        return "/recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        log.debug("Calling updateRecipe() method in Recipe Controller");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "/recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        log.debug("Calling saveOrUpdate() method in Recipe Controller");

        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + recipeCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Calling deleteById() method in Recipe Controller");

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
