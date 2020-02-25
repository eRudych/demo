package com.st.repository;

import java.util.List;

public interface OrderDetailsRepository {

    boolean addProduct(long orderId, long productId);
    boolean deleteProduct(long orderId, long id);
    List getAllProducts(long orderId);
    boolean deleteAllProducts(long orderId);
}
