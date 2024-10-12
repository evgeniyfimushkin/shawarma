package edu.evgen.shawarma;

import edu.evgen.shawarma.entities.Ingredient;
import edu.evgen.shawarma.data.IngredientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ShawarmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShawarmaApplication.class, args);
    }

    //  CommandLineRunner и ApplicationRunner - на этапе запуска приложения,
    //    после внедрения компонентов у этих классов вызовутся методы run
    // также могут использоваться для обработки аргументов командной строки.
    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            List<String> version = args.getOptionValues("version");
            repo.save(new Ingredient("PORK", "Свинина", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("BEEF", "Говядина", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("POTA", "Картошка", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("TMTO", "Помидоры", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("CUCM", "Огурцы", Ingredient.Type.VEGGIES));
            repo.save(new Ingredient("VITZ", "Сыр Витязь", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("GOLN", "Сыр Голандский", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("GARL", "Чесночный", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("CHES", "Сырный", Ingredient.Type.SAUCE));
        };
    }
}
