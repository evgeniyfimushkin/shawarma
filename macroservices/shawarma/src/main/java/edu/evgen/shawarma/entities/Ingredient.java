package edu.evgen.shawarma.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Ingredient implements Serializable {
    @Id
    private final String id;
    private final String name;
    private final Type type;
    @PersistenceCreator
    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public enum Type {
        PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
