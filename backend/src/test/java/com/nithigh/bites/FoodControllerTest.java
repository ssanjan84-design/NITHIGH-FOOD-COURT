package com.nithigh.bites;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

// BUG: Missing static imports
// BUG: MockitoBean may not exist for this Spring Boot version

@WebMvcTest(FoodControllers.class)
public class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FoodService foodService;

    @Test
    void healthShouldWork() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Backend is running"));
    }

    @Test
    void getFoodsShouldWork() throws Exception {
        mockMvc.perform(get("/api/food"))
                .andExpect(status().isCreated());
    }
}