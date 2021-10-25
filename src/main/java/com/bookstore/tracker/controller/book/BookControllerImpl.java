package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Autowired
    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping("/{bookId}")
    public String getBookById(@PathVariable("bookId") long bookId, Model bookModel) {
        Book foundBook = bookService.getBookById(bookId);
        if(null != foundBook){
            bookModel.addAttribute("book", foundBook);
        }

        return "book-update";
    }

    @Override
    @GetMapping("/list")
    public String getBookList(Model bookModel) {

        List<Book> bookList = bookService.getAllAvailableBooks();
        bookModel.addAttribute("bookList", bookList);

        return "book-list";
    }

    @Override
    public String deleteBookById(String bookId) {
        return null;
    }

    @Override
    @PostMapping("/save")
    public String saveBook() {
        return null;
    }
}
