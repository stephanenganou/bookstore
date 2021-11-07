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
    public BookServiceImpl(BookDao bookDao, UserService userService, BookViewMappingDao bookViewMappingDao) {
        this.bookDao = bookDao;
        this.userService = userService;
        this.bookViewMappingDao = bookViewMappingDao;
    }

    @Override
    @Transactional
    public BookDto getBookById(long bookId) {
        Book foundBook = bookDao.getById(bookId);
        return foundBook.convertToDto();
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
    public void deleteBookById(long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    @Transactional
    public Book saveBook(BookDto bookToSave) {
        Book book = bookDao.getById(bookToSave.getId());
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
    public void mapBookViewByUser(long bookId) {
        User loggedUser = userService.getLoggedUser();

        if (null != loggedUser) {
            log.info("User is authenticated");
            bookViewMappingDao.save(new BookViewMapping(bookId, loggedUser.getId()));
        }
    }

    @Override
    public void addRecommendationsForBook(List<RecommendedItem> RecommendedItems, Model bookModel) {
        List<BookDto> foundBookList = new ArrayList<>();

        for (RecommendedItem recommendedItem : RecommendedItems) {
            Book foundBook = bookDao.getById(recommendedItem.getItemID());
            if (null != foundBook) {
                foundBookList.add(foundBook.convertToDto());
            }
        }

        bookModel.addAttribute("recommendedBooks", foundBookList);
    }
}
