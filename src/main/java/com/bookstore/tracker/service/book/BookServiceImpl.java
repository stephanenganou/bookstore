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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
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

    @Override
    @Transactional
    public BookDto getBookById(final long bookId) {
        final Book foundBook = bookDao.getById(bookId);

        return (null == foundBook) ? null : foundBook.convertToDto();
    }

    @Override
    @Transactional
    public List<BookDto> getAllAvailableBooks() {
        return bookDao.findAll().stream()
                .map(Book::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBookById(final long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    @Transactional
    public Book saveBook(final BookDto bookToSave) {
        final Book book = bookDao.getById(bookToSave.getId());
        if (null != book) {
            book.setName(bookToSave.getName());
            book.setDescription(bookToSave.getDescription());
            book.setImage(bookToSave.getImage());
            book.setPrice(bookToSave.getPrice());

            return bookDao.save(book);
        } else {
            return null;
        }
    }

    @Override
    public void mapBookViewByUser(final long bookId) {
        final User loggedUser = userService.getLoggedUser();

        if (null != loggedUser) {
            log.info("User is authenticated");
            bookViewMappingDao.save(new BookViewMapping(bookId, loggedUser.getId()));
        }
    }

    @Override
    public void addRecommendationsForBook(final List<RecommendedItem> recommendedItems, Model bookModel) {
        final List<BookDto> recommendedBookList = new ArrayList<>();

        for (RecommendedItem recommendedItem : recommendedItems) {
            Book recommendedBook = bookDao.getById(recommendedItem.getItemID());
            if (null != recommendedBook) {
                recommendedBookList.add(recommendedBook.convertToDto());
            }
        }

        bookModel.addAttribute("recommendedBooks", recommendedBookList);
    }
}
