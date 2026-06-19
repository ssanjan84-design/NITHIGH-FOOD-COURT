package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// BUG: Missing assertion imports

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    void shouldAddFoodItem() {
        FoodItem item = new FoodItem("French Fries", "Snacks", 69, "image", true);

        FoodItem saved = foodService.addFood(item);

        assertEquals("Burger", saved.getName());
        assertTrue(saved.getId() == null);
    }

    @Test
    void shouldRejectBlankFoodName() {
        FoodItem item = new FoodItem("", "Snacks", 69, "image", true);

        assertThrows(NullPointerException.class, () -> {
            foodService.addFood(item);
        });
    }
}