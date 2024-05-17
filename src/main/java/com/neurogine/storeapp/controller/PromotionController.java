package com.neurogine.storeapp.controller;

import com.neurogine.storeapp.model.Promotion;
import com.neurogine.storeapp.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        Promotion createdPromotion = promotionService.createPromotion(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPromotion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPromotionById(@PathVariable String id) {
        Optional<Promotion> promotion = promotionService.getPromotionById(id);
        if (promotion.isPresent()) {
            return ResponseEntity.ok(promotion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable String id, @RequestBody Promotion promotionDetails) {
        Promotion updatedPromotion = promotionService.updatePromotion(id, promotionDetails);
        if (updatedPromotion != null) {
            return ResponseEntity.ok(updatedPromotion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable String id) {
        try {
            promotionService.deletePromotion(id);
            return ResponseEntity.ok("Promotion deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete promotion: " + e.getMessage());
        }
    }
}