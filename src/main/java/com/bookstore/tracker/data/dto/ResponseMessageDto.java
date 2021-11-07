package com.bookstore.tracker.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Data
public class ResponseMessageDto {

    @NotBlank(message = "This can not be blank")
    @NotNull(message = "this can not be null")
    private String message;

    private String cssClass;

    public ResponseMessageDto(@NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") String message,
                              String cssClass) {
        this.message = message;
        this.cssClass = cssClass;
    }

    @Override
    public String toString() {
        return "ResponseMessageDto{" +
                "message='" + message + '\'' +
                ", cssClass='" + cssClass + '\'' +
                '}';
    }

}