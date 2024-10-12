package edu.evgen.shawarma.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Shawarma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 2, message = "Имя как минимум из 2-х букв")
    private String name;

    @Size(min = 1, message = "Необходимо выбрать хотя бы один ингредиент")
    @ManyToMany() // объявление взаимосвязи между Shawarma и ingredients
    private List<Ingredient> ingredients;
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
}
