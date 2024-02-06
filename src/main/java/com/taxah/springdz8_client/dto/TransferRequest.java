package com.taxah.springdz8_client.dto;

import lombok.Data;

/**
 * Represents a transfer request for a product purchase.
 */
@Data
public class TransferRequest {
    private long productId;
    private long senderAccountId = 1;
    private long receiverAccountId = 2;
}
