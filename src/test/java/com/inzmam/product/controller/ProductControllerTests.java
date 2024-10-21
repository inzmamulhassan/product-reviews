package com.inzmam.product.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzmam.product.model.dto.ProductDto;
import com.inzmam.product.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDto productDto;
    @BeforeEach
    void setUp() {
        productDto = ProductDto.builder()
                .id(1L)
                .name("Product 1")
                .description("Product 1 description")
                .price(100.0)
                .build();
    }
    @Test
    void testSaveProduct() throws Exception {
        when(productService.saveProduct(any())).thenReturn(productDto);
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productDto.id()))
                .andExpect(jsonPath("$.name").value(productDto.name()))
                .andExpect(jsonPath("$.description").value(productDto.description()))
                .andExpect(jsonPath("$.price").value(productDto.price()));
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.findAllProducts()).thenReturn(List.of(productDto));
        ResultActions resultActions = mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(productDto.id()))
                .andExpect(jsonPath("$[0].name").value(productDto.name()))
                .andExpect(jsonPath("$[0].description").value(productDto.description()))
                .andExpect(jsonPath("$[0].price").value(productDto.price()));
    }

    @Test
    void testGetProductById() throws Exception {
        when(productService.findProductById(1L)).thenReturn(productDto);
        ResultActions resultActions = mockMvc.perform(get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productDto.id()))
                .andExpect(jsonPath("$.name").value(productDto.name()))
                .andExpect(jsonPath("$.description").value(productDto.description()))
                .andExpect(jsonPath("$.price").value(productDto.price()));
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(any())).thenReturn(productDto);
        ResultActions resultActions = mockMvc.perform(put("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productDto.id()))
                .andExpect(jsonPath("$.name").value(productDto.name()))
                .andExpect(jsonPath("$.description").value(productDto.description()))
                .andExpect(jsonPath("$.price").value(productDto.price()));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);
        mockMvc.perform(delete("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
