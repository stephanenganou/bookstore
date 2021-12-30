package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface plays the role of data access into the database for the table Authority.
 */
@Repository
@Transactional
public interface AuthorityDao extends JpaRepository<Authority, Long> {

}
