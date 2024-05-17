package com.neurogine.storeapp.repository;

import com.neurogine.storeapp.model.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PromotionRepository extends MongoRepository<Promotion, String> {
    // Custom query method to find promotions by their titles
    List<Promotion> findByTypeIn(List<String> titles);
}
