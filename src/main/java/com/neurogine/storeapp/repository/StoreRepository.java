package com.neurogine.storeapp.repository;

import com.neurogine.storeapp.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
    List<Store> findByNameContainingIgnoreCase(String name);
}
