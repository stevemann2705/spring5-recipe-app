package in.stevemann.spring5recipeapp.controllers;

import in.stevemann.spring5recipeapp.domain.Category;
import in.stevemann.spring5recipeapp.domain.UnitOfMeasure;
import in.stevemann.spring5recipeapp.repositories.CategoryRepository;
import in.stevemann.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(){

        Optional<Category> cat = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Tablespoon");

        System.out.println(cat.get().getId());
        System.out.println(uom.get().getId());
        return "index";
    }
}
