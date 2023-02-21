package com.flatrock.task.productsservice.repository;

import com.flatrock.task.productsservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByProductsId(Long productId);
}
