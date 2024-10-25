//package edu.evgen.shawarma.restcontroller;
//
//import edu.evgen.shawarma.data.ShawarmaRepository;
//import edu.evgen.shawarma.entities.Shawarma;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping(path = "/api/shawarmas",
//        produces = "application/json")
//@CrossOrigin(origins = "http://shawarmas:8080")
//public class ShawarmaRestController {
//
//    private final ShawarmaRepository shawarmaRepository;
//
//    @GetMapping(params = "recent")
//    public List<Shawarma> recentShawarmas() {
//        PageRequest pageRequest = PageRequest.of(
//                0, 12, Sort.by("createdAt").descending()
//        );
//        return shawarmaRepository.findAll(pageRequest).getContent();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Shawarma> getShawarmaById(@PathVariable("id") Long id) {
//        return shawarmaRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping(consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Shawarma postShawarma(@Valid @RequestBody Shawarma shawarma){
//        return shawarmaRepository.save(shawarma);
//    }
//
//}
