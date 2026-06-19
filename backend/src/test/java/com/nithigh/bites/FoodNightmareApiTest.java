package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FoodNightmareApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/api";
    }

    @Test
    void nightmare_healthShouldReturnExactMessage() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl() + "/health", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("NitHigh Bites backend is running", response.getBody());
    }

    @Test
    void nightmare_getFoodsShouldReturnSeedData() {
        ResponseEntity<FoodItem[]> response = restTemplate.getForEntity(baseUrl() + "/foods", FoodItem[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 4);
    }

    @Test
    void nightmare_searchShouldBeCaseInsensitive() {
        ResponseEntity<FoodItem[]> response = restTemplate.getForEntity(baseUrl() + "/foods/search?q=PIZZA", FoodItem[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        boolean found = List.of(response.getBody()).stream()
                .anyMatch(food -> food.getName().toLowerCase().contains("pizza"));

        assertTrue(found);
    }

    @Test
    void nightmare_unknownSearchShouldReturnEmptyArray() {
        ResponseEntity<FoodItem[]> response = restTemplate.getForEntity(baseUrl() + "/foods/search?q=notexistingfood999", FoodItem[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().length);
    }

    @Test
    void nightmare_categoryFilterShouldWork() {
        ResponseEntity<FoodItem[]> response = restTemplate.getForEntity(baseUrl() + "/foods/category?category=Beverages", FoodItem[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        boolean allMatch = List.of(response.getBody()).stream()
                .allMatch(food -> food.getCategory().toLowerCase().contains("beverages"));

        assertTrue(allMatch);
    }

    @Test
    void nightmare_wrongJsonFieldShouldFailForFood() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String wrongJson = "{\"foodName\":\"Wrong Field Burger\",\"type\":\"Fast Food\",\"foodPrice\":129}";

        ResponseEntity<String> response = restTemplate.postForEntity(
                baseUrl() + "/foods",
                new HttpEntity<>(wrongJson, headers),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void nightmare_wrongJsonFieldShouldFailForOrder() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String wrongJson = "{\"name\":\"Sanjan\",\"itemName\":\"Pizza\",\"qty\":1,\"amount\":199}";

        ResponseEntity<String> response = restTemplate.postForEntity(
                baseUrl() + "/orders",
                new HttpEntity<>(wrongJson, headers),
                String.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void nightmare_placeOrderShouldPersist() {
        FoodOrder order = new FoodOrder("Rahul", "Veg Pizza", 2, 398, "PLACED");

        ResponseEntity<FoodOrder> created = restTemplate.postForEntity(baseUrl() + "/orders", order, FoodOrder.class);

        assertEquals(HttpStatus.OK, created.getStatusCode());
        assertNotNull(created.getBody());
        assertNotNull(created.getBody().getId());

        ResponseEntity<FoodOrder[]> orders = restTemplate.getForEntity(baseUrl() + "/orders", FoodOrder[].class);

        boolean found = List.of(orders.getBody()).stream()
                .anyMatch(o -> "Rahul".equals(o.getCustomerName()) && "Veg Pizza".equals(o.getFoodName()));

        assertTrue(found);
    }
}