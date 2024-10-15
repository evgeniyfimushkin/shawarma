package edu.evgen.shawarma.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
//@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Ingredient{
    @Id
    private final String id;
    private final String name;
    private final Type type;
    @PersistenceConstructor
    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public enum Type {
        PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
