package com.inzmam.product.service.implementation;

import java.util.List;

import com.inzmam.product.model.dto.ProductDto;
import com.inzmam.product.model.mapper.ProductMapper;
import com.inzmam.product.repository.ProductRepository;
import com.inzmam.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductDto saveProduct(ProductDto product) {
         return productMapper.mapToDto(productRepository.save(productMapper.mapToEntity(product)));
    }

    @Override
    public ProductDto findProductById(Long id) {
        return productMapper.mapToDto(
                productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("record not found!")));
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        return productMapper.mapToDto(productRepository.save(productMapper.mapToEntity(product)));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productMapper.mapToDtoList(productRepository.findAll());
    }

}
