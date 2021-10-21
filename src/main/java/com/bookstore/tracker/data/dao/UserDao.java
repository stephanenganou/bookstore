package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String username);
}
