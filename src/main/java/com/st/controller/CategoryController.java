package com.st.controller;

import com.st.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("st-line/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/{id}")
    List getCategory(@PathVariable("id") long id) {
        return service.getCategory(id);
    }

    @GetMapping
    public List getAllCategories() {
        return service.getAllCategories();
    }
}
