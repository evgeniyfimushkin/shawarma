package edu.evgen.shawarma_admin.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Ingredient{
    private final String id;
    private final String name;
    private final Type type;
    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public enum Type {
        PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
