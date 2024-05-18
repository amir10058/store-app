package com.neurogine.storeapp.repository;

import com.neurogine.storeapp.model.Category;
import com.neurogine.storeapp.model.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    // Custom query method to find category by their names
    List<Category> findByNameIn(List<String> names);
}
