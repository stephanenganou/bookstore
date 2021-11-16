package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.dao.BookDao;
import com.bookstore.tracker.data.dao.BookViewMappingDao;
import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import com.bookstore.tracker.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private static final long VALID_BOOK_ID = 19923;

    private static final long INVALID_BOOK_ID = 9323;

    @Mock
    private BookDao bookDaoMock;

    @Mock
    private Book bookMock;

    @Mock
    private BookDto bookDtoMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private BookViewMappingDao bookViewMappingDaoMock;


    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookDaoMock, userServiceMock, bookViewMappingDaoMock);
    }

    @Test
    void whenGetBookById_withValidBookId_thenReturnBookDto() {
        // prepare
        when(bookDaoMock.getById(any(Long.class))).thenReturn(bookMock);
        when(bookMock.convertToDto()).thenReturn(bookDtoMock);

        // Test
        BookDto foundBook = bookService.getBookById(VALID_BOOK_ID);

        // Verify
        assertThat(foundBook, is(bookDtoMock));

    }

    @Test
    void whenGetBookById_withInValidBookId_thenReturnNull() {
        // prepare
        when(bookDaoMock.getById(any(Long.class))).thenReturn(null);

        // Test
        BookDto foundBook = bookService.getBookById(INVALID_BOOK_ID);

        // Verify
        assertNull(foundBook);
    }

    @Test
    void whenDeleteBookById_thenCallDaoDeleteByIdOnce() {
        // Prepare
        doNothing().when(bookDaoMock).deleteById(any(Long.class));

        // Test
        bookService.deleteBookById(VALID_BOOK_ID);

        // Verify
        verify(bookDaoMock, times(1)).deleteById(any(Long.class));

    }

    @Test
    void whenGetAllAvailableBooks_thenReturnBookDtoList() {
        // Prepare
        List<Book> bookList = new ArrayList<>();
        bookList.add(bookMock);
        when(bookDaoMock.findAll()).thenReturn(bookList);
        when(bookMock.convertToDto()).thenReturn(bookDtoMock);

        // Test
        List<BookDto> availableBooks = bookService.getAllAvailableBooks();

        // Verify
        assertThat(availableBooks.size(), is(1));
        assertThat(availableBooks.get(0), is(bookDtoMock));
    }
}