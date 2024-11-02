package edu.evgen.kitchen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
//@RestResource(rel = "shawoohas", path = "shawoohas")
public class Shawarma implements Serializable {
    private long id;

    @JsonIgnore
    @ToString.Exclude
    private ShawarmaOrder order;

    @NotNull
    @Size(min = 2, message = "Имя как минимум из 2-х букв")
    private String name;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Size(min = 1, message = "Необходимо выбрать хотя бы один ингредиент")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addShawarmaOrder(ShawarmaOrder order) {
        this.order = order;
    }
}
