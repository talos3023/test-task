package com.flatrock.task.productsservice.service;

import com.flatrock.task.productsservice.exception.ResourceNotFoundException;
import com.flatrock.task.productsservice.model.Category;
import com.flatrock.task.productsservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
    }

    public Category createCategory(Category category) {
        Category _category = new Category();
        _category.setName(category.getName());
        return categoryRepository.save(_category);
    }

    public Category updateCategory(long id, Category category) {
        Category _category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
        _category.setName(category.getName());
        return categoryRepository.save(_category);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
