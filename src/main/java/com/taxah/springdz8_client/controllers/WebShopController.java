package com.taxah.springdz8_client.controllers;

import com.taxah.springdz8_client.aspects.TrackUserAction;

import com.taxah.springdz8_client.dto.TransferBalance;
import com.taxah.springdz8_client.dto.TransferRequest;
import com.taxah.springdz8_client.model.Product;
import com.taxah.springdz8_client.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

/**
 * WebShopController class is a controller class responsible for handling web requests related to the web shop functionality.
 * It is annotated with @Controller to indicate that this class serves as a Spring MVC controller.
 */
@AllArgsConstructor
@Controller
@RequestMapping("/client")
public class WebShopController {
    private ShopService service;

    /**
     * Retrieves all products and the user's account balance to display in the shop page.
     *
     * @param model the model object for passing data to the view
     * @return the name of the view template to render
     */
    @GetMapping
    public String getAll(Model model) {
        List<Product> products = service.getAll();
        TransferBalance tr = service.getBalance();
        model.addAttribute("products", products);
        model.addAttribute("accountBalance", tr);
        return "shop";
    }

    /**
     * Handles the purchase request for a product.
     *
     * @param id    the ID of the product to be purchased
     * @param model the model object for passing data to the view
     * @return the name of the view template to render
     */
    @TrackUserAction
    @PostMapping("/buy")
    public String iWantToBuy(@RequestParam("productId") Long id, Model model) {
        try {
            TransferRequest tr = new TransferRequest();
            tr.setProductId(id);
            service.buyProduct(tr.getProductId());
            return "redirect:/client";
        } catch (HttpClientErrorException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
