package edu.evgen.shawarma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@Entity
//@RestResource(rel = "shawoohas", path = "shawoohas")
public class Shawarma implements Serializable {
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

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany
    @Size(min = 1, message = "Необходимо выбрать хотя бы один ингредиент")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addShawarmaOrder(ShawarmaOrder order) {
        this.order = order;
    }
}
