package com.bookstore.tracker.controller.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void whenGetLoginPage_withNonError_thenReturnLoginPage() throws Exception {

        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(content().string(containsString("Bookstore Login Page")));
    }

    @Test
    void whenGetLoginPage_withError_thenSetAttributeErrorToTrueInLoginPage() throws Exception {

        mockMvc.perform(get("/login")
                .param("error", "true"))
                .andDo(print())
                .andExpect(model().attribute("loginError", is(true)));
    }

    @Test
    void whenGetIndexPage_thenReturnHomePage() throws Exception {

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(content().string(containsString("Homepage")));
    }

    @Test
    void whenGetHomePage_thenReturnHomePage() throws Exception {

        mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(content().string(containsString("Homepage")));
    }
}
