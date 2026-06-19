# Test Error Answer Key

## FoodControllerTest.java

Fix wrong import:

```java
import org.springframework.boot.test.mock.mockito.MockBean;
```

Remove wrong `MockitoBean` import.

Change:

```java
@MockitoBean
```

to:

```java
@MockBean
```

Fix expected health text:

```java
NitHigh Bites backend is running
```

Fix endpoint:

```java
/api/foods
```

Fix status:

```java
status().isOk()
```

## FoodServiceTest.java

Add imports:

```java
import static org.junit.jupiter.api.Assertions.*;
```

Fix assertions:

```java
assertEquals("French Fries", saved.getName());
assertNotNull(saved.getId());
```

Fix exception:

```java
assertThrows(IllegalArgumentException.class, () -> foodService.addFood(item));
```

## FoodRepositoryTest.java

Fix method:

```java
findByNameContainingIgnoreCase
```

Fix assertion:

```java
assertFalse(result.isEmpty());
```