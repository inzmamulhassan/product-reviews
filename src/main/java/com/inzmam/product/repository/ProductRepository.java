package com.inzmam.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inzmam.product.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
