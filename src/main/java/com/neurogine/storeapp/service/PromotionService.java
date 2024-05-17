package com.neurogine.storeapp.service;

import com.neurogine.storeapp.model.Promotion;

import java.util.List;
import java.util.Optional;

public interface PromotionService {
    Promotion createPromotion(Promotion promotion);
    Optional<Promotion> getPromotionById(String id);
    List<Promotion> getAllPromotions();
    Promotion updatePromotion(String id, Promotion promotionDetails);
    void deletePromotion(String id);
    List<String> findPromotionIdsByTypes(List<String> types);
}
