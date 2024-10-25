package edu.evgen.shawarma.restcontroller;

import edu.evgen.shawarma.data.IngredientRepository;
import edu.evgen.shawarma.entities.Ingredient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class IngredientController {
    final private IngredientRepository repository;

    @GetMapping
    public List<Ingredient> allIngredienst() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findIngredientById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(ingredient -> ResponseEntity.ok(ingredient))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@Valid @RequestBody Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @PreAuthorize("hasRole(USER)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredientById(@PathVariable("id") String id){
        repository.deleteById(id);
    }
}
