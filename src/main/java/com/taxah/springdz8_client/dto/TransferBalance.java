package com.taxah.springdz8_client.dto;

import lombok.Data;

/**
 * Represents the balance transfer data between the user and the shop.
 */
@Data
public class TransferBalance {
    private double userBalance;
    private double shopBalance;
}
