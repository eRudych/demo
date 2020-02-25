package com.st.service.impl;

import com.st.entity.Order;
import com.st.repository.OrderDetailsRepository;
import com.st.repository.OrderRepository;
import com.st.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository detailsRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.create(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public boolean deleteOrder(long id) {
        return orderRepository.delete(id);
    }

//    @Override
//    public boolean close(long id) {
//        return orderRepository.close(id);
//    }
//
//    @Override
//    public boolean addProduct(long orderId, long productId) {
//        return detailsRepository.addProduct(orderId, productId);
//    }
//
//    @Override
//    public boolean deleteProduct(long orderId, long productId) {
//        return detailsRepository.deleteProduct(orderId, productId);
//    }
//
//    @Override
//    public List getAllProducts(long orderId) {
//        return detailsRepository.getAllProducts(orderId);
//    }
//
//    @Override
//    public boolean deleteAllProducts(long orderId) {
//        return detailsRepository.deleteAllProducts(orderId);
//    }

    @Override
    public Order createOrderForUser(Long userId) {
        log.info(userId.toString());
//        Order order= create(new Order(userId,"",""));
//        log.info(order.toString());
//        return order;
        return null;
    }

}
