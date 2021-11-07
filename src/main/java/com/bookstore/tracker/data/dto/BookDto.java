package com.bookstore.tracker.data.dto;

import com.bookstore.tracker.data.entity.Book;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Data
public class BookDto {

    private long id;

    @NotBlank(message = "This can not be blank")
    @NotNull(message = "this can not be null")
    private String name;

    @NotBlank(message = "This can not be blank")
    @NotNull(message = "this can not be null")
    @Min(10)
    private String description;

    private String image;

    @NotBlank(message = "This can not be blank")
    @NotNull(message = "this can not be null")
    private float price;

    public BookDto() {
    }

    public BookDto(long id, @NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") String name,
                   @NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") @Min(10) String description,
                   String image, @NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") float price) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Book convertToBook() {
        return new Book(id, name, description, image, price);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
