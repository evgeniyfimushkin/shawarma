package edu.evgen.shawarma.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Ingredient{
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
