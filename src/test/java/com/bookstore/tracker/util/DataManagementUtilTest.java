package com.bookstore.tracker.util;

import com.bookstore.tracker.helper.service.DataManagementUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class DataManagementUtilTest {

    private static final String EXCEL_CSV_HAPPY_HEADER = "application/vnd.ms-excel";
    private static final String EXCEL_CSV_UNHAPPY_HEADER = "application/.false";


    @Mock
    private MultipartFile mockMultipartFile;


    @Test
    void whenIsMultipartFileValid_WithValidCsvHeader_thenReturnTrue() {
        Mockito.when(mockMultipartFile.getContentType()).thenReturn(EXCEL_CSV_HAPPY_HEADER);

        boolean isInputFileValid = DataManagementUtil.isMultipartFileValid(mockMultipartFile);

        assertThat(isInputFileValid, is(true));
    }

    @Test
    void whenIsMultipartFileValid_WithUnValidCsvHeader_thenReturnFalse() {
        Mockito.when(mockMultipartFile.getContentType()).thenReturn(EXCEL_CSV_UNHAPPY_HEADER);

        boolean isInputFileValid = DataManagementUtil.isMultipartFileValid(mockMultipartFile);

        assertThat(isInputFileValid, is(false));
    }

}
