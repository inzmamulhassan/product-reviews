package com.inzmam.product.service;

import java.util.List;

import com.inzmam.product.model.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(ProductDto product);
    ProductDto findProductById(Long id);
    ProductDto updateProduct(ProductDto product);
    void deleteProduct(Long id);
    List<ProductDto> findAllProducts();
}
