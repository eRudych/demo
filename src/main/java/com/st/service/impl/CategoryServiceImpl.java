package com.st.service.impl;

import com.st.entity.Category;
import com.st.entity.Product;
import com.st.repository.ProductRepository;
import com.st.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final ProductRepository repository;

    @Override
    public List<Product> getCategory(long id) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.getAllCategories();
    }
}
