package edu.evgen.shawarma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@Entity

public class Shawarma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shawarma_order_id")
    @ToString.Exclude
    private ShawarmaOrder order;

    @NotNull
    @Size(min = 2, message = "Имя как минимум из 2-х букв")
    private String name;

    private Date createdAt = new Date();

    @ManyToMany
    @Size(min = 1, message = "Необходимо выбрать хотя бы один ингредиент")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addShawarmaOrder(ShawarmaOrder order){
        this.order = order;
    }
}
