package com.neurogine.storeapp.controller;

import com.neurogine.storeapp.dto.StoreRequestDTO;
import com.neurogine.storeapp.model.Store;
import com.neurogine.storeapp.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> getAllStores() {
        logger.debug("Request to get all stores");
        List<Store> stores = storeService.getAllStores();
        logger.info("Retrieved {} stores", stores.size());
        return stores;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable String id) {
        logger.debug("Request to get store with id: {}", id);
        Store store = storeService.getStoreById(id);
        logger.info("Retrieved store with id: {}", id);
        return ResponseEntity.ok(store);
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreRequestDTO storeRequestDTO) {
        logger.debug("Request to create a new store");
        Store createdStore = storeService.createStore(storeRequestDTO);
        logger.info("Created new store with id: {}", createdStore.getId());
        return ResponseEntity.status(201).body(createdStore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable String id, @RequestBody Store storeDetails) {
        logger.debug("Request to update store with id: {}", id);
        Store updatedStore = storeService.updateStore(id, storeDetails);
        logger.info("Updated store with id: {}", id);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable String id) {
        logger.debug("Request to delete store with id: {}", id);
        storeService.deleteStore(id);
        logger.info("Deleted store with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Store> searchStores(@RequestParam String query) {
        logger.debug("Request to search stores with query: {}", query);
        List<Store> stores = storeService.searchStores(query);
        logger.info("Retrieved {} stores for query: {}", stores.size(), query);
        return stores;
    }

    @PutMapping("/{id}/promo")
    public ResponseEntity<Store> updatePromo(@PathVariable String id, @RequestParam boolean promo) {
        logger.debug("Request to update promo status for store with id: {}", id);
        Store updatedStore = storeService.updatePromo(id, promo);
        logger.info("Updated promo status for store with id: {}", id);
        return ResponseEntity.ok(updatedStore);
    }

    @PostMapping("/{id}/promotions")
    public ResponseEntity<Store> addPromotion(@PathVariable String id, @RequestParam String promotionType) {
        logger.debug("Request to add promotion to store with id: {}", id);
        Store updatedStore = storeService.addPromotion(id, promotionType);
        logger.info("Added promotion to store with id: {}", id);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/{id}/promotions")
    public ResponseEntity<Store> removePromotion(@PathVariable String id, @RequestParam String promotionType) {
        logger.debug("Request to remove promotion from store with id: {}", id);
        Store updatedStore = storeService.removePromotion(id, promotionType);
        logger.info("Removed promotion from store with id: {}", id);
        return ResponseEntity.ok(updatedStore);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Store> addImage(@PathVariable String id,
                                               @RequestParam("file") MultipartFile imageFile) throws IOException {
        logger.debug("Request to add image to store with id: {}", id);
        Store updatedStore = storeService.addImage(id, imageFile);
        logger.info("Added image to store with id: {}", id);
        return ResponseEntity.ok(updatedStore);
    }
}