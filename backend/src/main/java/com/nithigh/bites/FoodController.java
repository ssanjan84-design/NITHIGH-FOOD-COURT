package com.nithigh.bites;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// BUG: Wrong import. This class/package does not exist.
import com.nithigh.bites.service.FoodServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodController {

    // BUG: Wrong type
    private final FoodServices foodService;

    // BUG: Wrong constructor type
    public FoodController(FoodServices foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodItem>> getFoods() {
        // BUG: GET should return 200, not 201
        return ResponseEntity.status(201).body(foodService.getAllFoods());
    }

    @PostMapping("/food")
    public ResponseEntity<FoodItem> addFood(@RequestBody FoodItem foodItem) {
        // BUG: endpoint should be /foods
        return ResponseEntity.ok(foodService.addFood(foodItem));
    }

    @GetMapping("/food/search")
    public ResponseEntity<List<FoodItem>> searchFoods(@RequestParam String keyword) {
        // BUG: endpoint should be /foods/search and param q
        return ResponseEntity.ok(foodService.searchFoods(keyword));
    }

    @GetMapping("/foods/category")
    public ResponseEntity<List<FoodItem>> getFoodsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(foodService.getFoodsByCategory(category));
    }

    @PostMapping("/order")
    public ResponseEntity<FoodOrder> placeOrder(@RequestBody FoodOrder order) {
        // BUG: endpoint should be /orders
        return ResponseEntity.ok(foodService.placeOrder(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<FoodOrder>> getOrders() {
        return ResponseEntity.ok(foodService.getAllOrders());
    }
}