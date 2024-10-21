package com.inzmam.product.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inzmam.product.model.dto.ProductDto;
import com.inzmam.product.model.mapper.ProductMapper;
import com.inzmam.product.repository.ProductRepository;
import com.inzmam.product.service.implementation.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Spy
    private ProductMapper productMapper;

    @Test
    void testSaveProduct() {
         ProductDto product = ProductDto.builder()
                 .id(1L)
                 .name("Product 1")
                 .description("Product 1 description")
                 .price(100.0)
                 .build();
        doReturn(productMapper.mapToEntity(product)).when(productRepository).save(any());

        final ProductDto productDto = productService.saveProduct(product);
        assertThat(productDto).isNotNull();
        assertThat(productDto.id()).isNotNull();
        assertThat(productDto.name()).isEqualTo(product.name());
        assertThat(productDto.description()).isEqualTo(product.description());
    }

    @Test
    void testFindProductById() {
        ProductDto product = ProductDto.builder()
                .id(2L)
                .name("Product 2")
                .description("Product 2 description")
                .price(200.0)
                .build();
        doReturn(Optional.of(productMapper.mapToEntity(product))).when(productRepository).findById(any());

        final ProductDto productDto = productService.findProductById(product.id());
        assertThat(productDto).isNotNull();
        assertThat(productDto.id()).isEqualTo(product.id());
        assertThat(productDto.name()).isEqualTo(product.name());
        assertThat(productDto.description()).isEqualTo(product.description());
    }

    @Test
    void testUpdateProduct() {
        ProductDto product = ProductDto.builder()
                .id(3L)
                .name("Product 3")
                .description("Product 3 description")
                .price(300.0)
                .build();
        doReturn(productMapper.mapToEntity(product)).when(productRepository).save(any());

        final ProductDto updatedProduct = productService.updateProduct(product);
        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.id()).isEqualTo(product.id());
        assertThat(updatedProduct.name()).isEqualTo(product.name());
        assertThat(updatedProduct.description()).isEqualTo(product.description());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(4L);
        verify(productRepository).deleteById(4L);
    }

    @Test
    void testFindAllProducts() {
        ProductDto product1 = ProductDto.builder()
                .id(5L)
                .name("Product 5")
                .description("Product 5 description")
                .price(500.0)
                .build();
        ProductDto product2 = ProductDto.builder()
                .id(6L)
                .name("Product 6")
                .description("Product 6 description")
                .price(600.0)
                .build();
        doReturn(productMapper.mapToDtoList(productRepository.findAll())).when(productRepository).findAll();

        final var products = productService.findAllProducts();
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).id()).isEqualTo(product1.id());
        assertThat(products.get(0).name()).isEqualTo(product1.name());
        assertThat(products.get(0).description()).isEqualTo(product1.description());
        assertThat(products.get(1).id()).isEqualTo(product2.id());
        assertThat(products.get(1).name()).isEqualTo(product2.name());
        assertThat(products.get(1).description()).isEqualTo(product2.description());
    }

}
