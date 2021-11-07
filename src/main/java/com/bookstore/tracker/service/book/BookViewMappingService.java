package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.entity.BookViewMapping;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public interface BookViewMappingService {

    void writeFileLocally(String fileName, List<BookViewMapping> dataList) throws IOException;
}
