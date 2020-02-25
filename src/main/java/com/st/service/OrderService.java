package com.st.service;


import com.st.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    Order updateOrder(Order order);

    boolean deleteOrder(long orderId);

//    boolean close(long orderId);
//
//    boolean addProduct(long orderId, long productId);
//
//    boolean deleteProduct(long orderId, long id);
//
//    List getAllProducts(long orderId);
//
//    boolean deleteAllProducts(long orderId);
//
    Order createOrderForUser(Long userId);
}
