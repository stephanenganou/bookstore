package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.dao.BookViewMappingDao;
import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.data.entity.BookViewMapping;
import com.bookstore.tracker.data.entity.User;
import com.bookstore.tracker.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This class helps to retrieve information to render to client.
 */
@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final UserService userService;
    private final BookViewMappingDao bookViewMappingDao;

    @Autowired
    public BookServiceImpl(final BookDao bookDao, final UserService userService,
                           final BookViewMappingDao bookViewMappingDao) {

        this.bookDao = bookDao;
        this.userService = userService;
        this.bookViewMappingDao = bookViewMappingDao;
    }

    /**
     * @see com.bookstore.tracker.service.book.BookService#getBookById(long)
     */
    @Override
    public BookDto getBookById(final long bookId) {
        try {
            final Book foundBook = bookDao.getById(bookId);
            return foundBook.convertToDto();
        } catch (EntityNotFoundException e) {
            log.error("The Book with the ID: {} , has not been found.", bookId);
            return null;
        }
    }

    /**
     * @see com.bookstore.tracker.service.book.BookService#getAllAvailableBooks()
     */
    @Override
    public List<BookDto> getAllAvailableBooks() {
        return bookDao.findAll().stream()
                .map(Book::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * @see com.bookstore.tracker.service.book.BookService#deleteBookById(long)
     */
    @Override
    public void deleteBookById(final long bookId) {
        try {
            bookDao.deleteById(bookId);
        } catch (EntityNotFoundException e) {
            log.error("The Book with Id {} cannot be deleted because it does not exist", bookId);
        }
    }

    /**
     * @see com.bookstore.tracker.service.book.BookService#saveBook(BookDto)
     */
    @Override
    public Book saveBook(final BookDto bookToSave) {
        Book returnBookAfterSave = null;
        try {
            if (isNewBook(bookToSave)) {
                returnBookAfterSave = bookDao.save(bookToSave.convertToBook());
            } else {
                returnBookAfterSave = saveBookWhenNew(bookToSave);
            }
        } catch (EntityNotFoundException e) {
            log.error("The following Book failed to be saved: {}", bookToSave);
            return returnBookAfterSave;
        }

        return returnBookAfterSave;
    }

    /**
     * @see com.bookstore.tracker.service.book.BookService#mapBookViewByUser(long)
     */
    @Override
    public void mapBookViewByUser(final long bookId) {
        final User loggedUser = userService.getLoggedUser();

        if (loggedUser != null) {
            log.info("User: {} is authenticated", loggedUser.getUserName());
            bookViewMappingDao.save(new BookViewMapping(bookId, loggedUser.getId()));
        }
    }

    /**
     * @see com.bookstore.tracker.service.book.BookService#addRecommendationsForBook(List, Model)
     */
    @Override
    public void addRecommendationsForBook(final List<RecommendedItem> recommendedItems, Model bookModel) {
        long lastRecommendedBookId = 0;
        try {
            final List<BookDto> recommendedBookList = new ArrayList<>();

            for (RecommendedItem recommendedItem : recommendedItems) {
                lastRecommendedBookId = recommendedItem.getItemID();
                final Book recommendedBook = bookDao.getById(lastRecommendedBookId);
                if (recommendedBook != null) {
                    recommendedBookList.add(recommendedBook.convertToDto());
                }
            }

            bookModel.addAttribute("recommendedBooks", recommendedBookList);
        } catch (EntityNotFoundException e) {
            log.error("The Book with the Id: {} does not exist", lastRecommendedBookId);
        }
    }

    private boolean isNewBook(final BookDto bookDto) {
        return bookDto.getId() == 0;
    }

    private Book saveBookWhenNew(final BookDto bookToSave) {

        try {
            final Book book = bookDao.getById(bookToSave.getId());
            if (book != null) {
                book.setName(bookToSave.getName());
                book.setDescription(bookToSave.getDescription());
                book.setImage(bookToSave.getImage());
                book.setPrice(bookToSave.getPrice());

                return bookDao.save(book);
            } else {
                return null;
            }
        } catch (EntityNotFoundException e) {
            log.error("The book with Id: {} does not exist", bookToSave.getId());
            return null;
        }
    }
}
