# NitHigh Bites Test Cases

## TC-01 Backend Startup
Run:

```bash
mvn spring-boot:run
```

Expected: backend starts on port 8080.

## TC-02 Health API

GET:

```text
http://localhost:8080/api/health
```

Expected:

```text
NitHigh Bites backend is running
```

## TC-03 Food Menu API

GET:

```text
http://localhost:8080/api/foods
```

Expected: 200 OK and food list.

## TC-04 Add Food

POST:

```text
/api/foods
```

Body:

```json
{
  "name": "French Fries",
  "category": "Snacks",
  "price": 69,
  "imageUrl": "image",
  "available": true
}
```

Expected: food saved.

## TC-05 Reject Blank Food Name

Expected: 400 Bad Request.

## TC-06 Reject Invalid Price

Expected: 400 Bad Request.

## TC-07 Search Food

GET:

```text
/api/foods/search?q=pizza
```

Expected: matching food items.

## TC-08 Place Order

POST:

```text
/api/orders
```

Body:

```json
{
  "customerName": "Sanjan",
  "foodName": "Veg Pizza",
  "quantity": 1,
  "totalPrice": 199,
  "status": "PLACED"
}
```

Expected: order saved.

## TC-09 Reject Invalid Order

Expected: 400 Bad Request.

## TC-10 Frontend

Expected:
- Menu loads
- Search works
- Cart works
- Place order works
- Orders refresh works

## TC-11 Tests

Run:

```bash
mvn test
```

Expected after fixing:

```text
BUILD SUCCESS
```