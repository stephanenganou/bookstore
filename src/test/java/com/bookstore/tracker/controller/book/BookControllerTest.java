package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {

    private static final long VALID_BOOK_ID = 11443683;
    private static final long INVALID_BOOK_ID = 11111;

    private MockMvc mockMvc;
    @Autowired
    private BookService bookService;
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
    void whenGetBookById_withExistingBookId_thenAddAttributeToMode() throws Exception {

        BookDto expectedBookDto = bookService.getBookById(VALID_BOOK_ID);
        mockMvc.perform(get("/book/{bookId}", VALID_BOOK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", is(expectedBookDto)));
    }

    @Test
    void whenGetBookById_nonExistingBookId_thenRedirectToNotFoundPage() throws Exception {

        mockMvc.perform(get("/book/{bookId}", INVALID_BOOK_ID))
                .andDo(print())
                .andExpect(status().is(302));
    }
}