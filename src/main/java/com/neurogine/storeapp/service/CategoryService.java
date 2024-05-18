package com.neurogine.storeapp.service;

import com.neurogine.storeapp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category promotion);
    Optional<Category> getCategoryById(String id);
    List<Category> getAllCategories();
    Category updateCategory(String id, Category promotionDetails);
    void deleteCategory(String id);
    List<String> findCategoryIdsByNames(List<String> names);
}
