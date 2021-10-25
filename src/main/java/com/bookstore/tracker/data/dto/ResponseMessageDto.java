package com.bookstore.tracker.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ResponseMessageDto {

    @NotBlank(message = "This can not be blank")
    @NotNull(message = "this can not be null")
    private String message;

    public ResponseMessageDto(@NotBlank(message = "This can not be blank") @NotNull(message = "this can not be null") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}