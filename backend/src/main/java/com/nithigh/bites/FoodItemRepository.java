package com.nithigh.bites;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    // BUG: Entity field is name, not names
    List<FoodItem> findByNamesContainingIgnoreCase(String name);

    List<FoodItem> findByCategoryContainingIgnoreCase(String category);
}