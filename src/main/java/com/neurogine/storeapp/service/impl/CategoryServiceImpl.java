package com.neurogine.storeapp.service.impl;

import com.neurogine.storeapp.model.Category;
import com.neurogine.storeapp.repository.CategoryRepository;
import com.neurogine.storeapp.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        logger.info("Creating new category: {}", category);
        Category createCategory = categoryRepository.save(category);
        logger.info("Category created successfully: {}", createCategory);
        return createCategory;
    }

    public Optional<Category> getCategoryById(String id) {
        logger.info("Fetching category by ID: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            logger.info("Category found: {}", category.get());
        } else {
            logger.info("Category not found for ID: {}", id);
        }
        return category;
    }

    public List<Category> getAllCategories() {
        logger.info("Fetching all categorys");
        List<Category> categorys = categoryRepository.findAll();
        logger.info("Found {} categorys", categorys.size());
        return categorys;
    }

    @Override
    public Category updateCategory(String id, Category categoryDetails) {
        logger.info("Updating category with ID: {}", id);
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDetails.getName());
            Category updatedCategory = categoryRepository.save(category);
            logger.info("Category updated successfully: {}", updatedCategory);
            return updatedCategory;
        } else {
            logger.warn("Category not found for update with ID: {}", id);
            return null;
        }
    }

    public void deleteCategory(String id) {
        logger.info("Deleting category with ID: {}", id);
        categoryRepository.deleteById(id);
        logger.info("Category deleted successfully with ID: {}", id);
    }

    public List<String> findCategoryIdsByNames(List<String> titles) {
        logger.info("Fetching category IDs by titles: {}", titles);
        List<Category> categories = categoryRepository.findByNameIn(titles);
        logger.info("Found categorys: {}", categories);
        return categories.stream().map(Category::getId).collect(Collectors.toList());
    }
}