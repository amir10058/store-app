package com.neurogine.storeapp.service.impl;

import com.neurogine.storeapp.dto.StoreRequestDTO;
import com.neurogine.storeapp.exception.ResourceNotFoundException;
import com.neurogine.storeapp.mappers.StoreMapper;
import com.neurogine.storeapp.model.Store;
import com.neurogine.storeapp.repository.StoreRepository;
import com.neurogine.storeapp.service.PromotionService;
import com.neurogine.storeapp.service.StoreService;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);
    private final StoreRepository storeRepository;
    private final PromotionService promotionService;
    private final StoreMapper storeMapper;


    public StoreServiceImpl(StoreRepository storeRepository, PromotionService promotionService, StoreMapper storeMapper) {
        this.promotionService = promotionService;
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    public List<Store> getAllStores() {
        logger.debug("Fetching all stores from the database");
        return storeRepository.findAll();
    }

    public Store getStoreById(String id) {
        logger.debug("Fetching store with id: {}", id);
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            return store.get();
        } else {
            logger.error("Store not found with id: {}", id);
            throw new ResourceNotFoundException("Store not found with id " + id);
        }
    }

    public Store createStore(StoreRequestDTO storeRequestDTO) {
        logger.debug("Creating a new store");
        //TODO: USE MAPSTRUCT OR DOZER INSTEAD OF SETTING ATTRIBUTES MANUALLY.
        Store store = storeMapper.convertDTOToEntity(storeRequestDTO);
        if (!CollectionUtils.isEmpty(storeRequestDTO.getPromotionTypes()))
            store.setPromotionIds(promotionService.findPromotionIdsByTypes(storeRequestDTO.getPromotionTypes()));
        return storeRepository.save(store);
    }

    public Store updateStore(String id, Store storeDetails) {
        logger.debug("Updating store with id: {}", id);
        Store store = getStoreById(id);
        store.setName(storeDetails.getName());
        store.setPromo(storeDetails.isPromo());
        store.setDeliveryTime(storeDetails.getDeliveryTime());
        store.setDistanceKm(storeDetails.getDistanceKm());
        store.setRating(storeDetails.getRating());
        store.setDeliveryFee(storeDetails.getDeliveryFee());
        store.setCategories(storeDetails.getCategories());
        store.setPromotionIds(storeDetails.getPromotionIds());
        return storeRepository.save(store);
    }

    public void deleteStore(String id) {
        logger.debug("Deleting store with id: {}", id);
        Store store = getStoreById(id);
        storeRepository.delete(store);
    }

    public List<Store> searchStores(String name) {
        logger.debug("Searching stores with query(name): {}", name);
        return storeRepository.findByNameContainingIgnoreCase(name);
    }

    public Store updatePromo(String id, boolean promo) {
        logger.debug("Updating promo status for store with id: {}", id);
        Store store = getStoreById(id);
        store.setPromo(promo);
        return storeRepository.save(store);
    }

    public Store addPromotion(String id, String promotionType) {
        logger.debug("Adding promotion to store with id: {}", id);
        Store store = getStoreById(id);
        List<String> promotionIdsByTypes = promotionService.findPromotionIdsByTypes(List.of(promotionType));
        if (store.getPromotionIds() != null) {
            store.getPromotionIds().addAll(promotionIdsByTypes);
        } else {
            store.setPromotionIds(promotionIdsByTypes);
        }
        return storeRepository.save(store);
    }

    public Store removePromotion(String id, String promotionType) {
        logger.debug("Removing promotion from store with id: {}", id);
        Store store = getStoreById(id);

        List<String> promotionIdsToRemoveByTypes = promotionService.findPromotionIdsByTypes(List.of(promotionType));

        store.setPromotionIds(store.getPromotionIds().stream()
                .filter(storePromotionId -> !promotionIdsToRemoveByTypes.contains(promotionType))
                .collect(Collectors.toList()));

        return storeRepository.save(store);
    }

    @Override
    public Store addImage(String id, MultipartFile imageFile) throws IOException {
        logger.debug("Adding image to store with id: {}", id);
        Store store = getStoreById(id);
        if (imageFile != null)
            store.setImage(new Binary(imageFile.getBytes()));
        return storeRepository.save(store);
    }
}