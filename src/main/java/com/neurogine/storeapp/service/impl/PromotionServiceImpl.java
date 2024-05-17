package com.neurogine.storeapp.service.impl;

import com.neurogine.storeapp.model.Promotion;
import com.neurogine.storeapp.repository.PromotionRepository;
import com.neurogine.storeapp.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(PromotionService.class);

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion createPromotion(Promotion promotion) {
        logger.info("Creating new promotion: {}", promotion);
        Promotion createdPromotion = promotionRepository.save(promotion);
        logger.info("Promotion created successfully: {}", createdPromotion);
        return createdPromotion;
    }

    public Optional<Promotion> getPromotionById(String id) {
        logger.info("Fetching promotion by ID: {}", id);
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if (promotion.isPresent()) {
            logger.info("Promotion found: {}", promotion.get());
        } else {
            logger.info("Promotion not found for ID: {}", id);
        }
        return promotion;
    }

    public List<Promotion> getAllPromotions() {
        logger.info("Fetching all promotions");
        List<Promotion> promotions = promotionRepository.findAll();
        logger.info("Found {} promotions", promotions.size());
        return promotions;
    }

    public Promotion updatePromotion(String id, Promotion promotionDetails) {
        logger.info("Updating promotion with ID: {}", id);
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        if (optionalPromotion.isPresent()) {
            Promotion promotion = optionalPromotion.get();
            promotion.setType(promotionDetails.getType());
            promotion.setDescription(promotionDetails.getDescription());
            Promotion updatedPromotion = promotionRepository.save(promotion);
            logger.info("Promotion updated successfully: {}", updatedPromotion);
            return updatedPromotion;
        } else {
            logger.warn("Promotion not found for update with ID: {}", id);
            return null;
        }
    }

    public void deletePromotion(String id) {
        logger.info("Deleting promotion with ID: {}", id);
        promotionRepository.deleteById(id);
        logger.info("Promotion deleted successfully with ID: {}", id);
    }

    public List<String> findPromotionIdsByTitles(List<String> titles) {
        logger.info("Fetching promotion IDs by titles: {}", titles);
        List<Promotion> promotions = promotionRepository.findByTypeIn(titles);
        logger.info("Found promotions: {}", promotions);
        return promotions.stream().map(Promotion::getId).collect(Collectors.toList());
    }
}