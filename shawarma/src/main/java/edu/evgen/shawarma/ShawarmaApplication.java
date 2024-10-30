package edu.evgen.shawarma;

import edu.evgen.shawarma.repository.OrderRepository;
import edu.evgen.shawarma.repository.ShawarmaRepository;
import edu.evgen.shawarma.repository.UserRepository;
import edu.evgen.shawarma.entities.Ingredient;
import edu.evgen.shawarma.repository.IngredientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class ShawarmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShawarmaApplication.class, args);

    }


    //  CommandLineRunner и ApplicationRunner - на этапе запуска приложения,
    //    после внедрения компонентов у этих классов вызовутся методы run
    // также могут использоваться для обработки аргументов командной строки.
    @Bean
    @Profile("!prod")
    public ApplicationRunner dataLoader(
            IngredientRepository repo,
            UserRepository userRepository,
            PasswordEncoder encoder,
            ShawarmaRepository shawarmaRepository,
            OrderRepository orderRepository
    ) {
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

//            Stream.of("Vadim", "Alex", "John")
//                    .map(name -> {
//                        Shawarma shawarma = new Shawarma();
//                        shawarma.setName(name);
//                        return shawarma;
//                    })
//                    .peek(shawarma -> shawarma.setIngredients(Arrays.asList(repo.findById("PORK").orElseThrow())))
//                    .peek(shawarma -> shawarma.setCreatedAt(new Date()))
//                    .forEach(shawarmaRepository::save);
        };

    }

    private static class ListParameterizedTypeReference extends ParameterizedTypeReference<List<Ingredient>> {
    }
}
