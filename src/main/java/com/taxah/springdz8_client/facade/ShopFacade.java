package com.taxah.springdz8_client.facade;

import com.taxah.springdz8_client.dto.TransferBalance;
import com.taxah.springdz8_client.dto.TransferRequest;
import com.taxah.springdz8_client.model.Product;
import com.taxah.springdz8_client.model.RequestStringDecorator;
import com.taxah.springdz8_client.service.ApiGatewayService;
import com.taxah.springdz8_client.service.FileGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The ShopFacade class provides methods to interact with the shop functionalities.
 */
@AllArgsConstructor
@Service
public class ShopFacade {

    private FileGateway fileGateway;
    private RequestStringDecorator requestStringDecorator;
    private final ApiGatewayService apiGatewayService;

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
        requestStringDecorator.setStringIdNumber(productId+"");
        fileGateway.writeToFile("UserRequestLog.txt", productId+"");
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
