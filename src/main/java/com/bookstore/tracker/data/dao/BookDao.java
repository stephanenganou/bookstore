package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
}
