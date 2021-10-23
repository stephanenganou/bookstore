package com.bookstore.tracker.data.entity;

import com.bookstore.tracker.helper.Auditable;

import javax.persistence.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Entity
@Table(name = "BOOK")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
