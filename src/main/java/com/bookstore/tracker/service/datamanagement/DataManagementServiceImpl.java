package com.bookstore.tracker.service.datamanagement;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.dto.BookDto;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class DataManagementServiceImpl implements DataManagementService {

    private final BookDao bookDao;

    @Autowired
    public DataManagementServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.setHeaderExtractionEnabled(true);
        CsvParser csvParser = new CsvParser(csvParserSettings);

        List<Record> parseAllRecords = csvParser.parseAllRecords(inputStream);
        processSave(parseAllRecords);
        log.info("Records saved!");
    }

    private void processSave(List<Record> recordList) {
        recordList.forEach(record -> {
            log.info("before parsing");
            String[] results = record.toString().split(";");
            BookDto bookDto = new BookDto();
            bookDto.setId(Long.parseLong(results[0]));
            bookDto.setName(results[1]);
            bookDto.setDescription(results[2]);
            bookDto.setImage(results[3]);
            bookDto.setPrice(Float.parseFloat(results[4]));
            log.info("after parsing");
            log.info("Example Data: {}", bookDto.getName());
            bookDao.save(bookDto.convertToBook());
        });
    }
}
