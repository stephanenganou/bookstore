package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.data.dto.BookDto;
import com.bookstore.tracker.helper.Auditable;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "BOOK")
@Data
public class Book extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "BOOK_NAME")
    private String name;

    @Column(name = "BOOK_DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "PRICE")
    private float price;

    public Book() {
    }

    public Book(long id, String name, String description, String image, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Book(String name, String description, String image, float price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public BookDto convertToDto() {
        return new BookDto(id, name, description, image, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
