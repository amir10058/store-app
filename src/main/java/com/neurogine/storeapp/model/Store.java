package com.neurogine.storeapp.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "stores")
@Data
public class Store {
    @Id
    private String id;
    private String name;
    private Binary image;
    private boolean promo;
    private int deliveryTime;
    private double distanceKm;
    private double rating;
    private double deliveryFee;
    private List<String> categoryIds; // List of Category IDs
    private List<String> promotionIds; // List of Promotion IDs
}