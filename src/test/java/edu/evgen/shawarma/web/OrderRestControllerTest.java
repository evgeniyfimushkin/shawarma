package edu.evgen.shawarma.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.evgen.shawarma.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class OrderRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    OrderRepository orderRepository;

//    @Test
//    public void testCreateOrder() throws Exception {
//        ShawarmaOrder order = new ShawarmaOrder();
//        order.setDeliveryName("Evgen");
//        order.setDeliveryStreet("Lenina10");
//        order.setCcNumber("4111111111111111");
//        order.setCcExpiration("12.12.2024");
//        order.setCcCVV("123");
//        Shawarma shawarma = new Shawarma();
//        shawarma.setCreatedAt(new Date());
//        shawarma.setOrder(order);
//        shawarma.setName("ShawarmaTest");
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient("TEST", "TEST", Ingredient.Type.PROTEIN));
//        shawarma.setIngredients(ingredients);
//        order.addShawarma(shawarma);
//        String json = objectMapper.writeValueAsString(order);
//
////        log.info("{}", order);
//        System.out.println(order);
//        mockMvc.perform(post("/api/orders")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.deliveryName").value("Evgen"));
//    }

    @Test
    public void testGetOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
