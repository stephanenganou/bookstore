package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.data.dto.BookDto;
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
@Table(name = "BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Book(String name, String description, String image, float price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    /**
     * Creates a new BookDto object based upon information of the calling object.
     */
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
