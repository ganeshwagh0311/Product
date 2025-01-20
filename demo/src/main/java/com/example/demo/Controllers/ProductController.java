package com.example.demo.Controllers;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.ProductRequest;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.ProductRepository;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam int page) {
        return productService.getAllProducts(PageRequest.of(page, 5));
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
        // Use productRequest to create a Product entity
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);

        productRepository.save(product);

        return ResponseEntity.ok("Product created successfully!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updateProdcut) {
        // Find the category by ID
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        existingProduct.setName(updateProdcut.getName());


        productRepository.save(existingProduct);

        return ResponseEntity.ok("product updated successfully!");
    }


    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

