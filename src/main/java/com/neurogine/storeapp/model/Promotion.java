package com.neurogine.storeapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "promotions")
public class Promotion {
    @Id
    private String id;
    private String description; // Additional details about the promotion
    private String type; // e.g., "Discounted Items", "Deals below RM30"
}
