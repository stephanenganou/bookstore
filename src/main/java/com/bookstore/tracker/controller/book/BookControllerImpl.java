package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
@Slf4j
@Lazy
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String getBookById(long bookId, Model bookModel) {
        log.info("getBookById with Id: {} and Model: {}", bookId, bookModel);
        BookDto foundBook = bookService.getBookById(bookId);
        if (null != foundBook) {
            bookModel.addAttribute("book", foundBook);
        }

        return "book-update";
    }

    @Override
    public String getBookList(Model bookModel) {
        log.info("getBookList with Model: {}", bookModel);
        List<BookDto> bookList = bookService.getAllAvailableBooks();
        if (null != bookList && bookList.size() > 0) {
            bookModel.addAttribute("bookList", bookList);
        }

        return "book-list";
    }

    @Override
    public String deleteBookById(long bookId, Model bookModel) {
        log.info("deleteBookById with Id: {} and Model: {}", bookId, bookModel);
        bookService.deleteBookById(bookId);

        return getBookList(bookModel);
    }

    @Override
    public String saveBook(BookDto book, Model bookModel) {
        log.info("saveBook with book: {} and Model: {}", book, bookModel);
        Book savedBook = bookService.saveBook(book);
        if (null != savedBook) {
            bookModel.addAttribute("success", true);
        }

        return "redirect:/book/list";
    }
}
