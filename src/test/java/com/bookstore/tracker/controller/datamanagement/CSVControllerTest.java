package com.bookstore.tracker.controller.datamanagement;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.service.book.BookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
public class CSVControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
        // TODO: Complete this Test later.Some how, post request are not easy to test with thymeleaf as template
    void whenUploadFile_withValidContain_thenAddBooks() throws Exception {
        // Prepare
        File file = new File("data/uploadData.csv");
        MockMultipartFile validUploadFile = new MockMultipartFile("uploadData", "uploadData.csv",
                MediaType.ALL_VALUE,
                Files.readAllBytes(file.toPath()));

        System.out.println(validUploadFile.isEmpty());

        List<BookDto> oldBookDtoList = bookService.getAllAvailableBooks();

        //Test + Verify
        mockMvc.perform(multipart("/data/upload").file(validUploadFile))
                .andExpect(status().isOk())
                .andExpect(model().attribute("bookList", hasSize(oldBookDtoList.size() + 2)));
    }

}
