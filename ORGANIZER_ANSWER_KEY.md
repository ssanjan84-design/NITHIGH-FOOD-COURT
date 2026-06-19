# Organizer Answer Key — NitHigh Bites Broken Version

## Backend Errors

### 1. Wrong import in `FoodController.java`

Remove:

```java
import com.nithigh.bites.service.FoodServices;
```

### 2. Wrong service type

Change:

```java
FoodServices
```

to:

```java
FoodService
```

### 3. Missing `@Service`

In `FoodService.java`, uncomment:

```java
@Service
```

### 4. Repository method typo

Change:

```java
findByNamesContainingIgnoreCase
```

to:

```java
findByNameContainingIgnoreCase
```

### 5. FoodItem missing no-args constructor

Add:

```java
public FoodItem() {}
```

### 6. FoodOrder missing no-args constructor

Add:

```java
public FoodOrder() {}
```

### 7. Wrong validation logic

In `FoodService.java`, blank food name and invalid price are currently saved.
They should throw `IllegalArgumentException`.

### 8. Wrong GET status

Change:

```java
ResponseEntity.status(201)
```

to:

```java
ResponseEntity.ok(...)
```

### 9. Wrong H2 property

Change:

```properties
spring.h2.console.enable=true
```

to:

```properties
spring.h2.console.enabled=true
```

### 10. Missing datasource initialization defer

Add:

```properties
spring.jpa.defer-datasource-initialization=true
```

## Frontend Errors

### 11. Wrong API port

Change:

```js
http://localhost:3000/api
```

to:

```js
http://localhost:8080/api
```

### 12. Wrong field names

Change:

```js
food.foodName
```

to:

```js
food.name
```

Change:

```js
food.foodPrice
```

to:

```js
food.price
```

### 13. Wrong search URL

Change:

```js
/food/search?keyword=
```

to:

```js
/foods/search?q=
```

### 14. Wrong order endpoint

Change:

```js
/order
```

to:

```js
/orders
```

### 15. Wrong JSON order fields

Use:

```js
customerName, foodName, quantity, totalPrice, status
```

## Test Errors

See `TEST_ERROR_ANSWER_KEY.md`.

---

# Nightmare Edition Extra Fixes

## Add Global Exception Handler

Create this file:

```java
package com.nithigh.bites;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
```

## FoodService validation should include

```java
if (foodItem.getName() == null || foodItem.getName().isBlank()) {
    throw new IllegalArgumentException("Food name is required");
}
if (foodItem.getCategory() == null || foodItem.getCategory().isBlank()) {
    throw new IllegalArgumentException("Food category is required");
}
if (foodItem.getPrice() <= 0) {
    throw new IllegalArgumentException("Food price must be greater than zero");
}
```

## Order validation should include

```java
if (order.getCustomerName() == null || order.getCustomerName().isBlank()) {
    throw new IllegalArgumentException("Customer name is required");
}
if (order.getFoodName() == null || order.getFoodName().isBlank()) {
    throw new IllegalArgumentException("Food name is required");
}
if (order.getQuantity() <= 0) {
    throw new IllegalArgumentException("Quantity must be greater than zero");
}
if (order.getTotalPrice() <= 0) {
    throw new IllegalArgumentException("Total price must be greater than zero");
}
if (order.getStatus() == null || order.getStatus().isBlank()) {
    order.setStatus("PLACED");
}
```

## FoodNightmareBrokenTest fixes

Add:

```java
import static org.junit.jupiter.api.Assertions.*;
```

Fix:

```java
assertEquals(4, 2 + 2);
assertEquals("NitHigh Bites", appName);
assertThrows(IllegalArgumentException.class, () -> {
    throw new IllegalArgumentException("Invalid input");
});
```
