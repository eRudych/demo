package com.st.repository;

import com.st.entity.Category;
import com.st.entity.Mark;
import com.st.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product create(Product product);

    boolean update(Product product);

    boolean remove(Long id);

    Product get(Long id);

    List<Product> getAll();

    List<Product> searchProducts(Product product);

    Category createCategory(String name);

    List getAllCategories();

    Mark createMark(String name);

    List getAllMarks();

    List getProductsFromCategory(long id);
}
