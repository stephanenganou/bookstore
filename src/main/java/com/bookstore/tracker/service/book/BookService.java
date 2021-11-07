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
 */
@Service
public interface BookService {

    BookDto getBookById(long bookId);

    List<BookDto> getAllAvailableBooks();

    void deleteBookById(long bookId);

    Book saveBook(BookDto bookToSave);

    void mapBookViewByUser(long bookId);

    void addRecommendationsForBook(List<RecommendedItem> bookRecommendations, Model bookModel);
}
