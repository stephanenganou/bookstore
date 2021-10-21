package com.bookstore.tracker.controller.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/book")
public interface BookController {

    @GetMapping("/{bookId}")
    String getBookById(@PathVariable("bookId") UUID bookId, Model bookModel);

    @GetMapping("/list")
    String getBookList(Model bookModel);

    @PostMapping("/save")
    String saveBook();
}
