package edu.evgen.shawarma.web

import com.fasterxml.jackson.databind.ObjectMapper
import edu.evgen.shawarma.entities.Ingredient
import edu.evgen.shawarma.entities.Shawarma
import edu.evgen.shawarma.entities.ShawarmaOrder
import feign.FeignException
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static edu.evgen.shawarma.entities.Ingredient.Type.PROTEIN
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Slf4j
@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
@EnableFeignClients
@RequiredArgsConstructor
class OrderRestControllerGTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    OrderRestClient orderRestClient

    @Autowired
    ObjectMapper objectMapper

    def "GetOrders MVC"(){
        expect:
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .response
                .contentAsString
                .startsWith('[{"id":1,"placedAt":')
    }

    def "get Orders Feign"() {
        expect:
        orderRestClient.orders.size() > 0
    }

    def "Post Invalid Data Failure"() {
        when:
        orderRestClient.newOrder(new ShawarmaOrder())

        then:
        FeignException.BadRequest exception = thrown()
        exception.status() == 400
        exception.contentUTF8() =~ /[{"timestamp":".*","status":400,"error":"Bad Request","path":"\\/api\\/orders"}]/
        objectMapper.readValue(exception.contentUTF8(), Map).subMap(["status", "error", "path"]) == [
                status: 400,
                error: "Bad Request",
                path: "/api/orders",
        ]
    }

    def "Post Success Test"() {
        when: "Get Orders Before"
        def ordersBefore = orderRestClient.orders

        and: "Prepare New Order"
        def shawarma = new Shawarma([
                name: "My Shawarma",
                ingredients: [new Ingredient("PORK", "Свинина", PROTEIN)]
        ])
        def newOrder = new ShawarmaOrder([
                deliveryName: "My deliveryName",
                deliveryStreet: "My deliveryStreet",
                ccNumber: "5562052625359088",
                ccExpiration: '24.09.2024',
                ccCVV: 301,
                shawarmas: [shawarma],
        ])

        and: "Post new Order"
        orderRestClient.newOrder(newOrder)

        and: "Get Orders After"
        def orders = orderRestClient.orders

        and: "Normalize NewOrder"
        newOrder.id = orders.last.id
        newOrder.shawarmas.first().id = orders.last.shawarmas.first().id

        then: "Check Orders"
        orders.take(orders.size() - 1) == ordersBefore
        orders.last == newOrder
    }
}
