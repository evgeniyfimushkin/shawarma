package edu.evgen.shawarma.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Shawarma {
    private Long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 2, message = "Имя как минимум из 2-х букв")
    private String name;
    @NotNull
    @Size(min = 1, message = "Необходимо выбрать хотя бы один ингредиент")
    private List<Ingredient> ingredients;
//    public void addIngredient(Ingredient ingredient){
//        ingredients.add(new IngredientRef(ingredient.getId()));
//    }
}
