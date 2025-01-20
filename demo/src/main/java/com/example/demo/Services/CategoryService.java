package com.example.demo.Services;


import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }


        public Category createCategory(Category category) {
            if (category.getProducts() != null) {
                for (Product product : category.getProducts()) {
                    product.setCategory(category); // Set the bidirectional relationship
                }
            }
            return categoryRepository.save(category);
        }




    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
