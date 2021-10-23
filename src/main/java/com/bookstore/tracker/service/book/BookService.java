package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public interface BookService {

    Book getBookById(long bookId);

    List<Book> getAllAvailableBooks();
}
