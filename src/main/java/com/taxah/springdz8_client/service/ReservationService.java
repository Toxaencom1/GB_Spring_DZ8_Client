package com.taxah.springdz8_client.service;


import com.taxah.springdz8_client.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * The ReservationService class provides methods to interact with the reservation storage.
 */
@AllArgsConstructor
@Component
public class ReservationService {
    private static final String STORAGE = "http://localhost:8079";
    private RestTemplate template;

    /**
     * Retrieves all products from API in the reservation storage.
     *
     * @return A ResponseEntity containing a list of products.
     */
    public ResponseEntity<List<Product>> getAllProducts() {
        String url = STORAGE + "/api";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<>() {
        };

        return template.exchange(
                url, HttpMethod.GET, requestEntity, responseType);
    }

}
