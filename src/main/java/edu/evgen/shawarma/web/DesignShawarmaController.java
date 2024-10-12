package edu.evgen.shawarma.web;


import edu.evgen.shawarma.entities.Ingredient;
import edu.evgen.shawarma.entities.Shawarma;
import edu.evgen.shawarma.entities.ShawarmaOrder;
import edu.evgen.shawarma.repositories.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import edu.evgen.shawarma.entities.Ingredient.Type;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawarmaOrder")
public class DesignShawarmaController {
    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignShawarmaController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processShawarma(@Valid Shawarma shawarma, Errors errors,
                                  @ModelAttribute ShawarmaOrder shawarmaOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        shawarmaOrder.addShawarma(shawarma);
        log.info("Processing shawarma: {}", shawarma);
        log.info("Current shawarma order {}", shawarmaOrder);
        return "redirect:/order/current";
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));

        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "shawarmaOrder")
    public ShawarmaOrder order() {
        return new ShawarmaOrder();
    }

    @ModelAttribute(name = "shawarma")
    public Shawarma shawarma() {
        return new Shawarma();
    }
}
