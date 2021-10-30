package com.bookstore.tracker.service.datamanagement;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.exception.CSVException;
import com.bookstore.tracker.helper.service.DataManagementUtil;
import com.univocity.parsers.common.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
@Slf4j
public class DataManagementServiceImpl implements DataManagementService {

    private static final int CSV_STRING_FIX_LENGTH = 5;
    private final BookDao bookDao;

    @Autowired
    public DataManagementServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(MultipartFile multipartFile) throws IOException {
        if (DataManagementUtil.isMultipartFileValid(multipartFile)) {
            try {
                List<Record> csvRecordList = DataManagementUtil.getRecordsFromCsvFile(multipartFile);
                processSave(csvRecordList);
            } catch (IOException e) {
                log.info("An exception occurred while saving the csv Records");
                throw new CSVException("CSV Parsing exception");
            }
        } else {
            log.info("Not acceptable file format: {}", multipartFile.getContentType());
        }
    }

    private void processSave(List<Record> recordList) {
        recordList.forEach(record -> {
            String[] results = record.toString().split(";");
            if (CSV_STRING_FIX_LENGTH == results.length) {
                BookDto bookDto = new BookDto(Long.parseLong(results[0]),
                        results[1], results[2], results[3],
                        Float.parseFloat(results[4]));

                saveBookInNotExist(bookDto.convertToBook());
            } else {
                log.info("This record has not the normal field length: {}", results.length);
            }
        });

    }

    private void saveBookInNotExist(Book bookRecord) {
        if (bookDao.findByName(bookRecord.getName()).isPresent()) {
            log.info("The Book with the name: {} does exist already!", bookRecord.getName());
        } else {
            Book savedBook = bookDao.save(bookRecord);
            log.info("Record saved!");
        }
    }


}
