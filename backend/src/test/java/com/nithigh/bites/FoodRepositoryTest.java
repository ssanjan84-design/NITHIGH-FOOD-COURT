package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

// BUG: Missing assertion import

@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Test
    void shouldSearchFoodByName() {
        foodItemRepository.save(new FoodItem("Chocolate Shake", "Beverages", 99, "image", true));

        List<FoodItem> result = foodItemRepository.findByFoodNameContainingIgnoreCase("chocolate");

        assertEquals(0, result.size());
    }
}