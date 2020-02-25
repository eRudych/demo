package com.st.service;

import com.st.entity.Category;
import com.st.entity.Product;

import java.util.List;

public interface CategoryService {

    List<Product> getCategory(long id);

    List<Category> getAllCategories();
}
