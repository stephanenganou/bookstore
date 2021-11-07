package com.bookstore.tracker.controller.book;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.service.book.BookService;
import com.bookstore.tracker.service.book.SimilarityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
@RequestMapping("/book")
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;
    private final SimilarityService similarityService;

    @Autowired
    public BookControllerImpl(final BookService bookService, final SimilarityService similarityService) {
        this.bookService = bookService;
        this.similarityService = similarityService;
    }

    @GetMapping("/{bookId}")
    @Override
    public String getBookById(@PathVariable("bookId") long bookId, Model bookModel) {
        log.info("GetBookById with Id: {} and Model: {}", bookId, bookModel);
        BookDto foundBook = bookService.getBookById(bookId);
        if (null != foundBook) {
            bookModel.addAttribute("book", foundBook);
            bookService.mapBookViewByUser(bookId);
            List<RecommendedItem> recommendedItems = similarityService.bookSimilarity(bookId, 5);
            log.info("Recommendations for bookId: {} are: {}", bookId, recommendedItems);

            bookService.addRecommendationsForBook(recommendedItems, bookModel);
        }

        return "book-update";
    }

    @GetMapping("/list")
    @Override
    public String getBookList(Model bookModel) {
        log.info("GetBookList with Model: {}", bookModel);
        List<BookDto> bookList = bookService.getAllAvailableBooks();
        if (null != bookList && bookList.size() > 0) {
            bookModel.addAttribute("bookList", bookList);
        }

        return "book-list";
    }

    @GetMapping("/delete/{bookId}")
    @Override
    public String deleteBookById(@PathVariable("bookId") long bookId, Model bookModel) {
        log.info("DeleteBookById with Id: {} and Model: {}", bookId, bookModel);
        bookService.deleteBookById(bookId);

        return getBookList(bookModel);
    }

    @PostMapping("/save")
    @Override
    public String saveBook(@ModelAttribute("book") BookDto book, Model bookModel) {
        log.info("SaveBook with book: {} and Model: {}", book, bookModel);
        Book savedBook = bookService.saveBook(book);
        if (null != savedBook) {
            bookModel.addAttribute("success", true);
        }

        return "redirect:/book/list";
    }
}
