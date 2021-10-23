package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.helper.Auditable;

import javax.persistence.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "BOOKVIEWMAPPING")
public class BookViewMapping extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "BOOK_ID")
    private long bookId;

    @Column(name = "USER_ID")
    private long userId;

    public BookViewMapping() {
    }

    public BookViewMapping(long id, long bookId, long userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
    }

    public BookViewMapping(long bookId, long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
