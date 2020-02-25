package com.st.repository;

import com.st.entity.Order;

public interface OrderRepository {

    Order create(Order order);

    boolean close(long id);

    boolean delete(long id);

    Order getOrder(long id);
}
