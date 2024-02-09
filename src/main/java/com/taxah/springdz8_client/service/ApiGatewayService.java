package com.taxah.springdz8_client.service;

import com.taxah.springdz8_client.dto.TransferBalance;
import com.taxah.springdz8_client.dto.TransferRequest;
import com.taxah.springdz8_client.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The ApiGatewayService class provides methods to manage external services.
 */
@AllArgsConstructor
@Component
public class ApiGatewayService {
    private static final String GATEWAY = "http://localhost:8765";
    private RestTemplate template;

    /**
     * Retrieves all products from API in the reservation storage.
     *
     * @return A ResponseEntity containing a list of products.
     */
    public ResponseEntity<List<Product>> getAllProducts() {
        String url = GATEWAY + "/store";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<>() {
        };

        return template.exchange(
                url, HttpMethod.GET, requestEntity, responseType);
    }
    /**
     * Initiates a purchase transaction.
     *
     * @param transferRequest The request containing information about the transfer.
     * @return A ResponseEntity containing information about the transaction.
     */
    public ResponseEntity<String> buy(TransferRequest transferRequest) {
        String url = GATEWAY + "/payment/transfer";
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
        String url = GATEWAY + "/payment/balance";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return template.exchange(
                url, HttpMethod.GET, requestEntity, TransferBalance.class);
    }
}
