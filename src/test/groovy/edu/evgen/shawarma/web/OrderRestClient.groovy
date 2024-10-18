package edu.evgen.shawarma.web

import edu.evgen.shawarma.entities.ShawarmaOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "order", url = "http://localhost:8080/")
interface OrderRestClient {

    @GetMapping("/api/orders")
    List<ShawarmaOrder> getOrders()

    @PostMapping("/api/orders")
    void newOrder(@RequestBody ShawarmaOrder order)
}
