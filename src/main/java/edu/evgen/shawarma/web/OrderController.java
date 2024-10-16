package edu.evgen.shawarma.web;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import edu.evgen.shawarma.data.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("shawarmaOrder")
public class OrderController {
    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid ShawarmaOrder shawarmaOrder,
            Errors errors,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        if(userDetails.getUsername()!=null)
            shawarmaOrder.setUsername(userDetails.getUsername());
        orderRepo.save(shawarmaOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
