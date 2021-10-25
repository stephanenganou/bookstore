package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public interface BookController {

    @GetMapping("/{bookId}")
    String getBookById(@PathVariable("bookId") long bookId, Model bookModel);

    @GetMapping("/list")
    String getBookList(Model bookModel);

    @GetMapping("/delete/{bookId}")
    String deleteBookById(@PathVariable("bookId") long bookId, Model bookModel);

    @PostMapping("/save")
    String saveBook(@ModelAttribute("book") BookDto book, Model bookModel);
}
