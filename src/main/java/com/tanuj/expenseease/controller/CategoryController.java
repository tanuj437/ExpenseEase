package com.tanuj.expenseease.controller;

import com.tanuj.expenseease.model.Category;
import com.tanuj.expenseease.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> all() { return categoryRepository.findAll(); }

    @GetMapping("/{id}")
    public Category byId(@PathVariable Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category c) {
        return ResponseEntity.ok(categoryRepository.save(c));
    }
}
