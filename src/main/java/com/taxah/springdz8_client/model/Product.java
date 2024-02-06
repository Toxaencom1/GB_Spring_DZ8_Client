package com.taxah.springdz8_client.model;

import lombok.Data;


/**
 * Represents a product in the web shop.
 */
@Data
public class Product {
    private Long id;
    private String name;
    private double cost;
    private boolean status = false;
}
