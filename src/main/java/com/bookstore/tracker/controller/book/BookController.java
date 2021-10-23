package com.bookstore.tracker.controller.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public interface BookController {

    String getBookById(long bookId, Model bookModel);

    String getBookList(Model bookModel);

    String saveBook();
}
