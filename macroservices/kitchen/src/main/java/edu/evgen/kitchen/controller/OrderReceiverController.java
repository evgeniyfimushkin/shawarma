package edu.evgen.kitchen.controller;

import edu.evgen.kitchen.entities.ShawarmaOrder;
import edu.evgen.kitchen.messaging.OrderReciever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {
    private final OrderReciever orderReciever;

    @GetMapping("/receive")
    public String receiveOrder(Model model){
        ShawarmaOrder order = orderReciever.receiveOrder();

        if(order != null){
            model.addAttribute("order",order);
            return "receiveOrder";
        }
        return "noOrder";
    }
}
