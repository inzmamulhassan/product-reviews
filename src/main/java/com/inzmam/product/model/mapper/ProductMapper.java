package com.inzmam.product.model.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.inzmam.product.model.dto.ProductDto;
import com.inzmam.product.model.entity.Product;

@Component
public class ProductMapper {
    public Product mapToEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.id())
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .build();
    }
    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public List<ProductDto> mapToDtoList(List<Product> all) {
        return all.stream().map(this::mapToDto).toList();
    }
}
