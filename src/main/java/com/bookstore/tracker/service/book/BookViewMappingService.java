package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.entity.BookViewMapping;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (BookViewMapping related) for classes which will implement it.
 */
@Service
public interface BookViewMappingService {

    /**
     * Method to write a file locally.
     */
    void writeFileLocally(final String fileName, final List<BookViewMapping> dataList) throws IOException;
}
