package com.bookstore.tracker.controller.other;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OtherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenRedirectToNotFoundPage_thenReturnNotFoundPage() throws Exception {

        mockMvc.perform(get("/notfound"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("NOT FOUND...")));
    }
}
