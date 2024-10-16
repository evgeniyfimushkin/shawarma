package edu.evgen.shawarma.web;

import edu.evgen.shawarma.data.OrderRepository;
import edu.evgen.shawarma.entities.ShawarmaOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class OrderRestController {

    private final OrderRepository orderRepository;

    @GetMapping("/orders")
    List<ShawarmaOrder> getOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .toList();
    }

    @PostMapping("/orders")
    public void newOrder(@RequestBody @Valid ShawarmaOrder order, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid order data: " + errors.getAllErrors());
        }
        log.info("Received new order: {}", order);
        orderRepository.save(order);
    }

    @GetMapping("/test")
    String testController() {
        return "design";
    }


}
