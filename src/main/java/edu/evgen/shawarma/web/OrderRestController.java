package edu.evgen.shawarma.web;

import edu.evgen.shawarma.data.OrderRepository;
import edu.evgen.shawarma.entities.ShawarmaOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderRestController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/orders")
    List<ShawarmaOrder> getOrders() {
        log.debug("getOrders <-");
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .toList();
    }

    @PostMapping("/api/orders")
    public void newOrder(@RequestBody @Valid ShawarmaOrder order) {
        log.info("Received new order: {}", order);
        order.getShawarmas().forEach(s -> s.setOrder(order));
        orderRepository.save(order);
    }
}
