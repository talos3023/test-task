package com.flatrock.task.productsservice.repository;

import com.flatrock.task.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoriesId(Long categoryId);
    List<Product> getProductByIdIn(List<Long> ids);
}
