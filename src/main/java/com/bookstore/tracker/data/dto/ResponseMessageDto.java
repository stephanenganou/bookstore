package com.bookstore.tracker.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
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

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}