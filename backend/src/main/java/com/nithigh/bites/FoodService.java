package com.nithigh.bites;

import org.springframework.stereotype.Service;
import java.util.List;

// BUG: @Service commented, so Spring may not detect this class
// @Service
public class FoodService {

    private final FoodItemRepository foodItemRepository;
    private final FoodOrderRepository foodOrderRepository;

    public FoodService(FoodItemRepository foodItemRepository, FoodOrderRepository foodOrderRepository) {
        this.foodItemRepository = foodItemRepository;
        this.foodOrderRepository = foodOrderRepository;
    }

    public List<FoodItem> getAllFoods() {
        return foodItemRepository.findAll();
    }

    public FoodItem addFood(FoodItem foodItem) {
        // BUG: Blank name should throw error, not save
        if (foodItem.getName() == null || foodItem.getName().isBlank()) {
            return foodItemRepository.save(foodItem);
        }

        // BUG: Invalid price should throw error, not save
        if (foodItem.getPrice() <= 0) {
            return foodItemRepository.save(foodItem);
        }

        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> searchFoods(String q) {
        return foodItemRepository.findByNamesContainingIgnoreCase(q);
    }

    public List<FoodItem> getFoodsByCategory(String category) {
        return foodItemRepository.findByCategoryContainingIgnoreCase(category);
    }

    public FoodOrder placeOrder(FoodOrder order) {
        // BUG: invalid order should not be saved
        if (order.getQuantity() <= 0) {
            return foodOrderRepository.save(order);
        }

        return foodOrderRepository.save(order);
    }

    public List<FoodOrder> getAllOrders() {
        return foodOrderRepository.findAll();
    }
}