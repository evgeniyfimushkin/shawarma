package edu.evgen.shawarma;

import edu.evgen.shawarma.data.OrderRepository;
import edu.evgen.shawarma.entities.Ingredient;
import edu.evgen.shawarma.entities.Shawarma;
import edu.evgen.shawarma.entities.ShawarmaOrder;
import edu.evgen.shawarma.web.OrderRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class OrderRestControllerTest {
//    @Mock
//    private OrderRepository orderRepository;
//    @InjectMocks

    @Autowired
    private OrderRestController orderRestController;
    private List<ShawarmaOrder> testListOrders;

//    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderRestController).build();
        testListOrders = testListOfShawarmaOrder();
    }

    @Test
    public void testGetOrders() throws Exception {

//        when(orderRepository.findAll()).thenReturn(testListOrders);
        assert !mockMvc.perform(get("/api/orders"))
                .andReturn()
                .getResponse()
                .getContentAsString()
                .isBlank();
//                .equals("");
//                .andExpect(status().isOk())
//
//                .andExpect(jsonPath("$[0].deliveryName").value("Evgen"))
//                .andExpect(jsonPath("$[0].deliveryStreet").value("Lenina10"))
//                .andExpect(jsonPath("$[0].shawarmas[0].name").value("ShawarmaTest"))
//                .andExpect(jsonPath("$[0].shawarmas[0].ingredients[0].name").value("TEST"));
    }
    @Test
    public void testPostOrders() throws Exception{

    }

    List<ShawarmaOrder> testListOfShawarmaOrder() {
        ShawarmaOrder order = new ShawarmaOrder();
        order.setDeliveryName("Evgen");
        order.setDeliveryStreet("Lenina10");
        order.setCcNumber("4111111111111111");
        order.setCcExpiration("12.12.2024");
        order.setCcCVV("123");
        Shawarma shawarma = new Shawarma();
        shawarma.setCreatedAt(new Date());
        shawarma.setName("ShawarmaTest");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("TEST", "TEST", Ingredient.Type.PROTEIN));
        shawarma.setIngredients(ingredients);
        order.addShawarma(shawarma);

        List<ShawarmaOrder> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }
}
