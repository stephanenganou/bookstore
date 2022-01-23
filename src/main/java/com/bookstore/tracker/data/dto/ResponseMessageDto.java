package com.bookstore.tracker.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This class helps to generate some Response messages to send to the user.
 */
@Data
@AllArgsConstructor
public class ResponseMessageDto {

    @NotBlank(message = "This can not be blank")
    @NotNull(message = "this can not be null")
    private String message;

    private String cssClass;

    @Override
    public String toString() {
        return "ResponseMessageDto{" +
                "message='" + message + '\'' +
                ", cssClass='" + cssClass + '\'' +
                '}';
    }

}