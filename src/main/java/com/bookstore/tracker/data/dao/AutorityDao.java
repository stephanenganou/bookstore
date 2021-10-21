package com.bookstore.tracker.data.dao;

import com.bookstore.tracker.data.entity.Autority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Repository
public interface AutorityDao extends JpaRepository<Autority, Long> {

    List<Autority> findByUserName(String userName);

}
