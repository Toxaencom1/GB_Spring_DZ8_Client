package com.taxah.springdz8_client.service;

import com.taxah.springdz8_client.dto.TransferBalance;
import com.taxah.springdz8_client.dto.TransferRequest;
import com.taxah.springdz8_client.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The ShopService class provides methods to interact with the shop functionalities.
 */
@RequiredArgsConstructor
@Service
public class ShopService {
    private  final ApiGatewayService apiGatewayService;

    /**
     * Retrieves all products available in the shop.
     *
     * @return A list of products.
     */
    public List<Product> getAll() {
        return apiGatewayService.getAllProducts().getBody();
    }

    /**
     * Initiates the process of purchasing a product.
     *
     * @param productId The ID of the product to be purchased.
     */
    public void buyProduct(Long productId) {
        TransferRequest tr = new TransferRequest();
        tr.setProductId(productId);
        apiGatewayService.buy(tr);
    }

    /**
     * Retrieves the balance of the user and shop.
     *
     * @return The balance information.
     */
    public TransferBalance getBalance() {
        return apiGatewayService.getBalance().getBody();
    }
}
