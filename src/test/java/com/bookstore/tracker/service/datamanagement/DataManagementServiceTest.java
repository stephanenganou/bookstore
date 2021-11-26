package com.bookstore.tracker.service.datamanagement;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.exception.CSVException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class DataManagementServiceTest {

    @Autowired
    private BookDao bookDao;

    private DataManagementService dataManagementService;

    @Mock
    private DataManagementService dataManagementServiceMock;

    @Mock
    private MockMultipartFile inValidMultipartFile;

    @BeforeEach
    void setUp() {
        dataManagementService = new DataManagementServiceImpl(bookDao);
    }

    @Test
    void whenSave_withValidMultipartFile_thenSaveDataInDatabase() throws IOException {

        // Prepare
        File file = new File("data/uploadData.csv");
        MockMultipartFile validUploadFile = new MockMultipartFile("uploadData", "uploadData.csv",
                MediaType.ALL_VALUE,
                Files.readAllBytes(file.toPath()));

        // Verify
        assertDoesNotThrow(() -> dataManagementService.save(validUploadFile));
    }

    @Test
    void whenSave_withInValidMultipartFile_thenThrowCSVException() throws IOException {

        // Prepare
        doThrow(new CSVException("Invalid File"))
                .when(dataManagementServiceMock)
                .save(any(MultipartFile.class));

        // Verify
        assertThrows(CSVException.class, () -> dataManagementServiceMock.save(inValidMultipartFile));
    }
}