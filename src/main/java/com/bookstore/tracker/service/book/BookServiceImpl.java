package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public BookDto getBookById(long bookId) {
        Book foundBook = bookDao.getById(bookId);
        return foundBook.convertToDto();
    }

    @Override
    @Transactional
    public List<BookDto> getAllAvailableBooks() {
        return bookDao.findAll().stream()
                .map(Book::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBookById(long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    @Transactional
    public Book saveBook(BookDto bookToSave) {
        if (null != bookToSave) {
            return bookDao.save(bookToSave.convertToBook());
        } else {
            return null;
        }
    }
}
