package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Repository
public interface AuthorityDao extends JpaRepository<Authority, Long> {

}
