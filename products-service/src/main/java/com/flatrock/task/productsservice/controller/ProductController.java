package com.flatrock.task.productsservice.controller;

import com.flatrock.task.productsservice.model.Category;
import com.flatrock.task.productsservice.model.Product;
import com.flatrock.task.productsservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping("/products/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        return new ResponseEntity<>(service.getAllProductsByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/categories")
    public ResponseEntity<List<Category>> getAllCategoriesByProductId(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>(service.getAllCategoriesByProductId(productId), HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryId}/products")
    public ResponseEntity<Product> addProduct(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Product productRequest) {
        return new ResponseEntity<>(service.addProduct(categoryId, productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product productRequest) {
        return new ResponseEntity<>(service.updateProduct(id, productRequest), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<HttpStatus> deleteProductFromCategory(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "productId") Long productId) {
        service.deleteProductFromCategory(categoryId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
