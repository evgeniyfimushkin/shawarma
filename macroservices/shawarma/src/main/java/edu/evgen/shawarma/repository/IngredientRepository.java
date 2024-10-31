package edu.evgen.shawarma.repository;

import edu.evgen.shawarma.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,String> {
//    List<Ingredient> findAll();
//    Optional<Ingredient> findById(String id);
//    Ingredient save(Ingredient ingredient);
}
