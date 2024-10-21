package com.inzmam.product.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.inzmam.product.model.entity.Product;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveProduct() {
         Product product = Product.builder()
                 .name("Product 1")
                 .description("Product 1 description")
                 .price(100.0)
                 .createdBy("admin")
                 .updatedBy("admin")
                 .createdAt("2021-09-01")
                 .updatedAt("2021-09-01")
                 .build();
         productRepository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertNotNull(productRepository.findById(product.getId()));
    }

    @Test
    void testFindProductById() {
        Product product = Product.builder()
                .name("Product 2")
                .description("Product 2 description")
                .price(200.0)
                .createdBy("admin")
                .updatedBy("admin")
                .createdAt("2021-09-01")
                .updatedAt("2021-09-01")
                .build();
        productRepository.save(product);
        Product savedProduct = productRepository.findById(product.getId()).orElse(null);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(product.getName(), savedProduct.getName());
        Assertions.assertEquals(product.getDescription(), savedProduct.getDescription());
        Assertions.assertEquals(product.getPrice(), savedProduct.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Product product = Product.builder()
                .name("Product 3")
                .description("Product 3 description")
                .price(300.0)
                .createdBy("admin")
                .updatedBy("admin")
                .createdAt("2021-09-01")
                .updatedAt("2021-09-01")
                .build();
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        Assertions.assertTrue(productRepository.findById(product.getId()).isEmpty());
    }

    @Test
    void testUpdateProduct() {
        Product product = Product.builder()
                .name("Product 4")
                .description("Product 4 description")
                .price(400.0)
                .createdBy("admin")
                .updatedBy("admin")
                .createdAt("2021-09-01")
                .updatedAt("2021-09-01")
                .build();
        productRepository.save(product);
        Product savedProduct = productRepository.findById(product.getId()).orElse(null);
        Assertions.assertNotNull(savedProduct);
        savedProduct.setName("Product 4 Updated");
        savedProduct.setDescription("Product 4 description Updated");
        savedProduct.setPrice(500.0);
        productRepository.save(savedProduct);
        Product updatedProduct = productRepository.findById(product.getId()).orElse(null);
        Assertions.assertNotNull(updatedProduct);
        Assertions.assertEquals(savedProduct.getName(), updatedProduct.getName());
        Assertions.assertEquals(savedProduct.getDescription(), updatedProduct.getDescription());
        Assertions.assertEquals(savedProduct.getPrice(), updatedProduct.getPrice());
    }

    @Test
    void testFindAllProducts() {
        Product product1 = Product.builder()
                .name("Product 5")
                .description("Product 5 description")
                .price(500.0)
                .createdBy("admin")
                .updatedBy("admin")
                .createdAt("2021-09-01")
                .updatedAt("2021-09-01")
                .build();
        productRepository.save(product1);
        Product product2 = Product.builder()
                .name("Product 6")
                .description("Product 6 description")
                .price(600.0)
                .createdBy("admin")
                .updatedBy("admin")
                .createdAt("2021-09-01")
                .updatedAt("2021-09-01")
                .build();
        productRepository.save(product2);
        Iterable<Product> products = productRepository.findAll();
        Assertions.assertNotNull(products);
        Assertions.assertTrue(products.iterator().hasNext());
    }

}
