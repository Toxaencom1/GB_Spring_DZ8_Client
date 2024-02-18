package com.taxah.springdz8_client.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestStringDecorator {
    private String stringIdNumber;
    private static RequestStringDecorator instance;

    private RequestStringDecorator() {
    }
    public static RequestStringDecorator getInstance() {
        if (instance == null) {
            instance = new RequestStringDecorator();
        }
        return instance;
    }
    public String decorate(String stringIdNumber) {
        return "Request: User reserve product. Id: " + stringIdNumber + " TimeStamp: " + LocalDateTime.now();
    }
}
