package com.taxah.springdz8_client.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * The RequestStringDecorator class provides functionality to decorate request strings.
 */
@Data
public class RequestStringDecorator {
    private String stringIdNumber;
    private static RequestStringDecorator instance;

    /**
     * The singleton instance of the RequestStringDecorator.
     */
    private RequestStringDecorator() {
    }

    /**
     * Gets the singleton instance of the RequestStringDecorator.
     *
     * @return The singleton instance.
     */
    public static RequestStringDecorator getInstance() {
        if (instance == null) {
            instance = new RequestStringDecorator();
        }
        return instance;
    }

    /**
     * Decorates the string with additional information.
     *
     * @param stringIdNumber The ID number to decorate.
     * @return The decorated string.
     */
    public String decorate(String stringIdNumber) {
        return "Request: User reserve product. Id: " + stringIdNumber + " TimeStamp: " + LocalDateTime.now();
    }
}
