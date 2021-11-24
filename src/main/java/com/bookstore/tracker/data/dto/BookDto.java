package com.bookstore.tracker.data.dto;

import com.bookstore.tracker.data.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public BookDto(@NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") String name,
                   @NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") @Min(10) String description,
                   String image, @NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") float price) {

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
