package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (Book related) for classes which will implement it.
 */
@Service
public interface BookService {

    /**
     * Method that returns a BookDto to the client based upon the parameter bookId.
     */
    BookDto getBookById(final long bookId);

    /**
     * Method that returns a list of available Books (as Dto) to the client.
     */
    List<BookDto> getAllAvailableBooks();

    /**
     * Method to delete a book by its ID.
     */
    void deleteBookById(final long bookId);

    /**
     * Method to save a BookDto and returns a book object.
     */
    Book saveBook(final BookDto bookToSave);

    /**
     * Method to map a user on a book. This will be used for the book recommendation.
     */
    void mapBookViewByUser(final long bookId);

    /**
     * Method recommendations a book view.
     */
    void addRecommendationsForBook(final List<RecommendedItem> bookRecommendations, final Model bookModel);
}
