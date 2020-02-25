package com.st.controller;

import com.st.entity.Order;
import com.st.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("st-line/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Order createOrder(Long userId) {
        System.out.println(userId);
        log.info(userId.toString());
        return service.createOrderForUser(userId);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Order updateOrder(Order order) {
        return service.updateOrder(order);
    }

    @DeleteMapping
    public boolean deleteOrder(@RequestBody long id) {
        return service.deleteOrder(id);
    }

//    @PostMapping(value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public Order addProduct(@RequestBody long id) {
//        return service.addProduct(id);
//    }
//

//    @DeleteMapping("/product")
//    public boolean deleteProduct(@RequestBody long id) {
//        return service.deleteProduct(id);id
//    }
//
//    @GetMapping("/products")
//    public List getAllProducts() {
//        return service.getAllProducts();
//    }
//
//    @DeleteMapping("/products")
//    public boolean deleteAllProducts() {
//        return service.deleteAllProducts();
//    }
}
