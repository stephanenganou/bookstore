package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    private final

    @Override
    @GetMapping("/{bookId}")
    public String getBookById(@PathVariable("bookId") long bookId, Model bookModel) {
        return null;
    }

    @Override
    @GetMapping("/list")
    public String getBookList(Model bookModel) {
        List<Book> bookList = new ArrayList<>();
        bookModel.addAttribute("bookList", bookList);

        return "book-list";
    }

    @Override
    @PostMapping("/save")
    public String saveBook() {
        return null;
    }
}
