package com.bookstore.tracker.service.datamanagement;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataManagementServiceImpl implements DataManagementService {

    private final BookDao bookDao;

    @Autowired
    public DataManagementServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<BookDto> bookDtoList = CSVHelper.csvToBookDto(file.getInputStream());
            bookDao.saveAll(bookDtoList
                    .stream()
                    .map(BookDto::convertToBook)
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream loadBookData() {
        List<Book> availableBookList = bookDao.findAll();

        return CSVHelper.BookToCSV(availableBookList);
    }
}
