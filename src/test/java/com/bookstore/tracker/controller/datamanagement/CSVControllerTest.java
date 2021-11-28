package com.bookstore.tracker.controller.datamanagement;

import com.bookstore.tracker.service.book.BookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
        MockMultipartFile validUploadFile = new MockMultipartFile("file", "file",
                "text/.csv",
                Files.readAllBytes(file.toPath()));

        int oldBookDtoListSize = bookService.getAllAvailableBooks().size();

        //Test + Verify
        mockMvc.perform(multipart("/data/upload")
                .file(validUploadFile)
        )
                .andExpect(status().is(302));

        int expectedBookDtoListSize = bookService.getAllAvailableBooks().size();
        assertThat(expectedBookDtoListSize, is(oldBookDtoListSize + 2));

    }

}
