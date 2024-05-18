package com.neurogine.storeapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class StoreRequestDTO {
    private String name;
    private boolean promo;
    private int deliveryTime;
    private double distanceKm;
    private double rating;
    private double deliveryFee;
    private List<String> categoryNames;
    private List<String> promotionTypes;
}