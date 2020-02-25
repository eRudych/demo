package com.st.controller;

import com.st.entity.Category;
import com.st.entity.Mark;
import com.st.entity.Product;
import com.st.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("st-line/productsFun")
@RequiredArgsConstructor
@Slf4j
public class ProductsController {

    private final ProductService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    Product create(@RequestBody Product product) {
        log.info(product.toString());
        return service.create(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    Product update(@RequestBody Product product) {
        return service.update(product);
    }

    @DeleteMapping("/{id}")
    boolean remove(@PathVariable("id") long id) {
        return service.remove(id);
    }

    @GetMapping("/{id}")
    Product get(@PathVariable("id") long id) {
        return service.get(id);
    }

    @GetMapping
    List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping(value = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE})
    List searchProducts(@RequestBody Product product) {
        return service.searchProducts(product);
    }

    @PostMapping("/createCategory")
    Category createCategory(@RequestBody String name) {
        return service.createCategory(name);
    }

    @PostMapping("/createMark")
    Mark createMark(@RequestBody String name) {
        return service.createMark(name);
    }

    @GetMapping("/categories")
    List<Category> getAllCategories() {
        log.info("go");
        return service.getAllCategories();
    }

    @GetMapping("/marks")
    List<Mark> getAllMarks() {
        return service.getAllMarks();
    }
}
