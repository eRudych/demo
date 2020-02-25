package com.st.service;

import com.st.entity.Category;
import com.st.entity.Mark;
import com.st.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product update(Product product);

    boolean remove(Long id);

    Product get(Long id);

    List getAll();

    List searchProducts(Product post);

    Category createCategory(String name);

    List<Category> getAllCategories();

    Mark createMark(String name);

    List<Mark> getAllMarks();
}
