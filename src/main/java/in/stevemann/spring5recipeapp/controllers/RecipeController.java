package in.stevemann.spring5recipeapp.controllers;

import in.stevemann.spring5recipeapp.commands.RecipeCommand;
import in.stevemann.spring5recipeapp.exceptions.NotFoundException;
import in.stevemann.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    public final RecipeService recipeService;

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

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

        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        log.debug("Calling updateRecipe() method in Recipe Controller");

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + recipeCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Calling deleteById() method in Recipe Controller");

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
