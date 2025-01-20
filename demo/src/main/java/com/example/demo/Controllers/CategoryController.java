package com.example.demo.Controllers;


import com.example.demo.Entity.Category;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public Page<Category> getAllCategories(@RequestParam int page) {
        return categoryService.getAllCategories(PageRequest.of(page, 10));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty");
        }


        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));


        existingCategory.setName(updatedCategory.getName());


        categoryRepository.save(existingCategory);

        return ResponseEntity.ok("Category updated successfully!");
    }


    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
