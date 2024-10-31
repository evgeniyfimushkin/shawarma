package edu.evgen.shawarma_admin.service;

import edu.evgen.shawarma_admin.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();
    Ingredient addIngredient(Ingredient ingredient);
}
