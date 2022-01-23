package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface plays the role of data access into the database for the table BookViewMapping.
 */
@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * Finds a specify User by its name.
     *
     * @return User
     */
    User findByUserName(String username);
}
