package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
@Lazy
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBookById(long bookId) {
        return bookDao.getById(bookId);
    }

    @Override
    public List<Book> getAllAvailableBooks() {
        return bookDao.findAll();
    }
}
