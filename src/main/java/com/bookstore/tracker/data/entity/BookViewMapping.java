package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.helper.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "BOOKVIEWMAPPING")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookViewMapping extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "BOOK_ID")
    private long bookId;

    @Column(name = "USER_ID")
    private long userId;

    public BookViewMapping(long bookId, long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookViewMapping{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
