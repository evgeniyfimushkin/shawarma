package edu.evgen.shawarma.controller;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import edu.evgen.shawarma.repository.OrderRepository;
import edu.evgen.shawarma.service.JmsOrderMessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("shawarmaOrder")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepo;
    private final JmsOrderMessagingService messagingService;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid ShawarmaOrder shawarmaOrder,
            Errors errors,
            SessionStatus sessionStatus,
            Principal principal
    ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        // Установка имени пользователя
        String username = getUsernameFromPrincipal(principal);
        if (username != null) {
            shawarmaOrder.setUsername(username);
        }

        // Сохранение заказа
        orderRepo.save(shawarmaOrder);
        messagingService.sendOrder(shawarmaOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    private String getUsernameFromPrincipal(Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            // Извлечение имени из OAuth2 атрибутов
            return oAuth2User.getAttribute("name");
        } else if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        // Альтернативный способ — использование имени по умолчанию
        return principal.getName();
    }
}
