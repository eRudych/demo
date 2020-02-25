package com.st.service.impl;

import com.st.entity.Category;
import com.st.entity.Mark;
import com.st.entity.Product;
import com.st.repository.ProductRepository;
import com.st.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.create(product);
    }

    @Override
    public Product update(Product product) {
        if (repository.update(product))
            return get(product.getId());
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return repository.remove(id);
    }

    @Override
    public Product get(Long id) {
        return repository.get(id);
    }

    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Product> searchProducts(Product product) {
        return repository.searchProducts(product);
    }

    @Override
    public Category createCategory(String name) {
        return repository.createCategory(name);
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("go...");
        return repository.getAllCategories();
    }

    @Override
    public Mark createMark(String name) {
        return repository.createMark(name);
    }

    @Override
    public List<Mark> getAllMarks() {
        return repository.getAllMarks();
    }
}
