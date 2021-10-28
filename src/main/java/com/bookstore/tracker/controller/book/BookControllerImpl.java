package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
@RequestMapping("/book")
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    @Override
    public String getBookById(long bookId, Model bookModel) {
        log.info("getBookById with Id: {} and Model: {}", bookId, bookModel);
        BookDto foundBook = bookService.getBookById(bookId);
        if (null != foundBook) {
            bookModel.addAttribute("book", foundBook);
        }

        return "book-update";
    }

    @GetMapping("/list")
    @Override
    public String getBookList(Model bookModel) {
        log.info("getBookList with Model: {}", bookModel);
        List<BookDto> bookList = bookService.getAllAvailableBooks();
        if (null != bookList && bookList.size() > 0) {
            bookModel.addAttribute("bookList", bookList);
        }

        return "book-list";
    }

    @GetMapping("/delete/{bookId}")
    @Override
    public String deleteBookById(long bookId, Model bookModel) {
        log.info("deleteBookById with Id: {} and Model: {}", bookId, bookModel);
        bookService.deleteBookById(bookId);

        return getBookList(bookModel);
    }

    @PostMapping("/save")
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
