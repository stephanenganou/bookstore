package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.service.book.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class BookControllerIT {

    private static final long VALID_BOOK_ID = 11443683;

    private static final long INVALID_BOOK_ID = 11111;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

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

    @Test
    void whenGetBookList_thenReturnBookListPage() throws Exception {

        List<BookDto> expectedBookDtoList = bookService.getAllAvailableBooks();
        mockMvc.perform(get("/book/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookList", is(expectedBookDtoList)));
    }

    @Test
    void whenDeleteBookById_withExistingBookId_thenDeleteBook() throws Exception {

        List<BookDto> expectedBookDtoList = bookService.getAllAvailableBooks();
        mockMvc.perform(get("/book/delete/{bookId}", VALID_BOOK_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookList", hasSize(expectedBookDtoList.size() - 1)));
    }

    @Test
    void whenSaveBook_thenAddBookInDBAndReturnToBookListPage() throws Exception {

        BookDto bookToSave = new BookDto("TestBook", "Test Book Description", "", 13);
        int expectedBookDtoListLength = bookService.getAllAvailableBooks().size() + 1;
        mockMvc.perform(post("/book/save", VALID_BOOK_ID)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("book", bookToSave)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookList", hasSize(expectedBookDtoListLength)));
    }
}