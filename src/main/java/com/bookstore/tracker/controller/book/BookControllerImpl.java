package com.bookstore.tracker.controller.book;

import org.springframework.ui.Model;

import java.util.UUID;

public class BookControllerImpl implements BookController {
    @Override
    public String getBookById(UUID bookId, Model bookModel) {
        return null;
    }

    @Override
    public String getBookList(Model bookModel) {
        return "book-list";
    }

    @Override
    public String saveBook() {
        return null;
    }
}
