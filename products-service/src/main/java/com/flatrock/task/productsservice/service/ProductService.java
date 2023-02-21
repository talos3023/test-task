package com.flatrock.task.productsservice.service;

import com.flatrock.task.productsservice.exception.ResourceNotFoundException;
import com.flatrock.task.productsservice.model.Category;
import com.flatrock.task.productsservice.model.Product;
import com.flatrock.task.productsservice.repository.CategoryRepository;
import com.flatrock.task.productsservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Not found Category with id = " + categoryId);
        }
        return productRepository.findProductsByCategoriesId(categoryId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
    }

    public List<Category> getAllCategoriesByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Not found Product  with id = " + productId);
        }
        return categoryRepository.findCategoriesByProductsId(productId);
    }

    public Product addProduct(Long categoryId, Product productRequest) {
        return categoryRepository.findById(categoryId).map(category -> {
            long productId = productRequest.getId();
            // product is existed
            if (productId != 0L) {
                Product _product = productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + productId));
                category.addProduct(_product);
                categoryRepository.save(category);
                return _product;
            }
            // add and create new Product
            category.addProduct(productRequest);
            return productRepository.save(productRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + categoryId));
    }

    public Product updateProduct(long id, Product productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + "not found"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        return productRepository.save(product);
    }

    public List<Product> decreaseQuantity(Set<Long> ids) {
        List<Product> products = productRepository.getProductByIdIn(new ArrayList<>(ids));
        products.forEach(product -> {
            int quantity = product.getQuantity();
            quantity--;
            if (quantity < 0) {
                throw new RuntimeException("Not enough products: " + product.getName());
            }
            product.setQuantity(quantity);
        });
        return productRepository.saveAll(products);
    }

    public void deleteProductFromCategory(Long categoryId, Long productId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + categoryId));
        category.removeProduct(productId);
        categoryRepository.save(category);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
