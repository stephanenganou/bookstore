package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
public interface BookController {

    String getAddBookPage(Model model);

    String getBookById(long bookId, Model bookModel);

    String getBookList(Model bookModel);

    String deleteBookById(long bookId, Model bookModel);

    String saveBook(BookDto book, Model bookModel);
}
