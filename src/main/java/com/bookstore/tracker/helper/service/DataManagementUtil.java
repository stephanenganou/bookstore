package com.bookstore.tracker.helper.service;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This class helps in supporting the traffic of Data.
 */
@Slf4j
public final class DataManagementUtil {

    private static final String[] VALID_HEADERS = {"text/.csv", "application/vnd.ms-excel"};

    /**
     * This method checks if a given MultipartFile is valid.
     */
    public static boolean isMultipartFileValid(final MultipartFile multipartFile) {
        log.info("File Contain Type: {}", multipartFile.getContentType());
        return Arrays.stream(VALID_HEADERS).anyMatch
                (s -> Objects.requireNonNull(multipartFile.getContentType()).contains(s));
    }

    /**
     * This method extracts information or records in the csv file that has been inputed.
     */
    public static List<Record> getRecordsFromCsvFile(final MultipartFile multipartFile) throws IOException {
        final InputStream inputStream = multipartFile.getInputStream();
        final CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.setHeaderExtractionEnabled(true);
        final CsvParser csvParser = new CsvParser(csvParserSettings);

        return csvParser.parseAllRecords(inputStream);
    }
}
