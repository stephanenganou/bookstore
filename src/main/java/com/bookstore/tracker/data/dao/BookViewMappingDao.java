package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.BookViewMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface plays the role of data access into the database for the table BookViewMapping.
 */
@Repository
@Transactional
public interface BookViewMappingDao extends JpaRepository<BookViewMapping, Long> {

    /**
     * Finds a specify BookViewMapping by its ID.
     *
     * @return List<BookViewMapping>
     */
    List<BookViewMapping> findByBookId(long bookId);
}
