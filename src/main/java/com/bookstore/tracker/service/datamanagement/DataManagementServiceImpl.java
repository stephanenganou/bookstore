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

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This class handles the transfer of Data from csvFiles into the system.
 */
@Service
@Slf4j
public class DataManagementServiceImpl implements DataManagementService {

    private static final int CSV_STRING_FIX_LENGTH = 5;
    private final BookDao bookDao;

    @Autowired
    public DataManagementServiceImpl(final BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * @see com.bookstore.tracker.service.datamanagement.DataManagementService#save(MultipartFile)
     */
    @Override
    public void save(final MultipartFile multipartFile) {
        if (DataManagementUtil.isMultipartFileValid(multipartFile)) {
            try {
                final List<Record> csvRecordList = DataManagementUtil.getRecordsFromCsvFile(multipartFile);
                processSave(csvRecordList);
            } catch (IOException e) {
                log.error("An exception occurred while saving the csv Records");
                throw new CSVException("CSV Parsing exception");
            }
        } else {
            log.info("Not acceptable file format: {}", multipartFile.getContentType());
        }
    }

    private void processSave(final List<Record> recordList) {
        recordList.forEach(record -> {
            final String[] results = record.toString().split(";");
            if (CSV_STRING_FIX_LENGTH == results.length) {
                final BookDto bookDto = new BookDto(Long.parseLong(results[0]),
                        results[1], results[2], results[3],
                        Float.parseFloat(results[4]));

                saveBookIfNotExist(bookDto.convertToBook());
            } else {
                log.info("This record has not the normal field length: {}", results.length);
            }
        });

    }

    private void saveBookIfNotExist(Book bookRecord) {
        try {
            if (bookDao.findByName(bookRecord.getName()).isPresent()) {
                log.info("The Book with the name: {} does exist already!", bookRecord.getName());
            } else {
                bookDao.save(bookRecord);
                log.info("Record saved!");
            }
        } catch (EntityNotFoundException e) {
            log.error("The System couldn't find the the Book: {}", bookRecord);
        }
    }


}
