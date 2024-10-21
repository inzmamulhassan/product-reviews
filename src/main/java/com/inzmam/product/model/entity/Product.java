package com.inzmam.product.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    /**
     *  I have omitted the following fields for simplicity
     */
//    private String category;
//    private String brand;
//    private String color;
//    private String size;
//    private String sku;
//    private String model;
//    private String manufacturer;
//    private String madeIn;
//    private String image;
//    private String url;
//    private String status;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
}
