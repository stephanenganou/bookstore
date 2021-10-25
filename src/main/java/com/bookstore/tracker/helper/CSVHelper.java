package com.bookstore.tracker.helper;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.data.entity.Book;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "Name", "Description", "Image", "Price"};

    public static boolean hasCSVFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static List<BookDto> csvToBookDto(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<BookDto> bookDtoList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                BookDto bookDto = new BookDto(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Name"),
                        csvRecord.get("Description"),
                        csvRecord.get("Image"),
                        Float.parseFloat(csvRecord.get("Price"))
                );

                bookDtoList.add(bookDto);
            }

            return bookDtoList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream BookToCSV(List<Book> bookList) {
        final CSVFormat format = CSVFormat.DEFAULT
                .withQuoteMode(QuoteMode.MINIMAL)
                .withHeader(HEADERs);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Book book : bookList) {
                List<String> bookData = Arrays.asList(
                        String.valueOf(book.getId()),
                        book.getName(),
                        book.getDescription(),
                        book.getImage(),
                        String.valueOf(book.getPrice())
                );

                csvPrinter.printRecord(bookData);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
