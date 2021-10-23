package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.BookViewMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Repository
public interface BookViewMappingDao extends JpaRepository<BookViewMapping, Long> {

    List<BookViewMapping> findByBookId(long bookId);
}
