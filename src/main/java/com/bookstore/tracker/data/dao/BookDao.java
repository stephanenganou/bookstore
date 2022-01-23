package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface plays the role of data access into the database for the table Book.
 */
@Repository
@Transactional
public interface BookDao extends JpaRepository<Book, Long> {

    /**
     * Finds a specify book by its name.
     *
     * @return Optional<book>
     */
    Optional<Book> findByName(String name);

}
