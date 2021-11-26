package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.entity.BookViewMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
@Slf4j
public class BookViewMappingServiceImpl implements BookViewMappingService {

    @Override
    public void writeFileLocally(final String fileName,
                                 final List<BookViewMapping> dataList) throws IOException {

        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for (BookViewMapping bookViewMapping : dataList) {
            bufferedWriter.write(bookViewMapping.getUserId()
                    + "," + bookViewMapping.getBookId()
                    //+ "," + getDateInStringFormat(bookViewMapping.getCreateDate())
                    + "\n"
            );
        }
        bufferedWriter.close();
    }

    // TODO: add timeStamp in the csv file.
    private String getDateInStringFormat(final Date date) {
        return (null == date) ? "" : DateFormat.getDateInstance().format(date);
    }
}
