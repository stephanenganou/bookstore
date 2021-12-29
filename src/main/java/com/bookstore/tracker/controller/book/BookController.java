package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods for classes which will implement it.
 */
@Controller
public interface BookController {

    /**
     * Method rendering the Page to add a new book in the system.
     */
    String getAddBookPage(Model model);

    /**
     * Method responsible to a specific book based upon its ID.
     */
    String getBookById(long bookId, Model bookModel);

    /**
     * Method rendering the page with all available book in the system.
     */
    String getBookList(Model bookModel);

    /**
     * Method used to delete a book by ID.
     */
    String deleteBookById(long bookId, Model bookModel);

    /**
     * Method used to save a book in the system.
     */
    String saveBook(BookDto book, Model bookModel);
}
