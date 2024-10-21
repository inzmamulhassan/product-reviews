package com.inzmam.product.model.dto;

import lombok.Builder;

@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        Double price
) {
}
