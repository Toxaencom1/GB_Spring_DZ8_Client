package com.taxah.springdz8_client.service;

import com.taxah.springdz8_client.dto.TransferBalance;
import com.taxah.springdz8_client.dto.TransferRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The TransactionService class provides methods to manage transactions.
 */
@AllArgsConstructor
@Component
public class TransactionService {
    private static final String STORAGE = "http://localhost:8081";
    private RestTemplate template;

    /**
     * Initiates a purchase transaction.
     *
     * @param transferRequest The request containing information about the transfer.
     * @return A ResponseEntity containing information about the transaction.
     */
    public ResponseEntity<String> buy(TransferRequest transferRequest) {
        String url = STORAGE + "/transfer";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransferRequest> requestEntity = new HttpEntity<>(transferRequest, headers);
        return template.exchange(
                url, HttpMethod.POST, requestEntity, String.class);
    }

    /**
     * Retrieves the current balance information.
     *
     * @return A ResponseEntity containing the balance information.
     */
    public ResponseEntity<TransferBalance> getBalance() {
        String url = STORAGE + "/balance";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return template.exchange(
                url, HttpMethod.GET, requestEntity, TransferBalance.class);
    }
}
