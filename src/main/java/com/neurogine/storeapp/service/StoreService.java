package com.neurogine.storeapp.service;

import com.neurogine.storeapp.dto.StoreRequestDTO;
import com.neurogine.storeapp.model.Store;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StoreService {
    List<Store> getAllStores();
    Store getStoreById(String id);
    Store createStore(StoreRequestDTO store);
    Store updateStore(String id, Store storeDetails);
    void deleteStore(String id);
    List<Store> searchStores(String query);
    Store updatePromo(String id, boolean promo);
    Store addPromotion(String id, String promotion);
    Store removePromotion(String id, String promotionType);
    Store addImage(String id, MultipartFile imageFile) throws IOException;

}
