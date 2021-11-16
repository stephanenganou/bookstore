package com.bookstore.tracker.entitydatautil;

import com.bookstore.tracker.data.entity.BookViewMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
public final class EntityFactory {

    public static List<BookViewMapping> getBookViewMappingList() {
        List<BookViewMapping> mappingList = new ArrayList<>();

        BookViewMapping bookViewMapping_1 = new BookViewMapping(43442333, 20443683);
        BookViewMapping bookViewMapping_2 = new BookViewMapping(43442333, 20443683);
        BookViewMapping bookViewMapping_3 = new BookViewMapping(43442333, 20443689);
        BookViewMapping bookViewMapping_4 = new BookViewMapping(43442333, 20443688);

        mappingList.add(bookViewMapping_1);
        mappingList.add(bookViewMapping_2);
        mappingList.add(bookViewMapping_3);
        mappingList.add(bookViewMapping_4);

        return mappingList;
    }

    public static BookViewMapping getBookViewMapping() {

        return new BookViewMapping(43442333, 20443683);
    }
}
