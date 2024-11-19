package com.java.coding.controller;

import com.java.coding.entity.Product;
import com.java.coding.service.ProductService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    public void testGetProductByIdFound() throws Exception {
        Product product = new Product("1", "A", 1.00, "descA");
        when(productService.getProductById("1")).thenReturn(Optional.of(product));
        String found = new String(Files.readAllBytes(
                Paths.get("src/test/resources/singleProductResponse.json")));
        mockMvc.perform(get("/api/product/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(found));
    }

    @Test
    @Disabled(value = "TBD")
    void testGetProductByIdNotFound() throws Exception {
        Product product = new Product("2", "A", 1.00, "descA");
        when(productService.getProductById(product.getId())).thenReturn(Optional.empty());
        String notFound = new String(Files.readAllBytes(
                Paths.get("src/test/resources/productNotFound.json")));
        mockMvc.perform(get("/api/product/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(notFound));
    }

    @Test
    void testGetAllProducts() throws Exception {
        List<Product> product = Arrays.asList(new Product("1", "A", 1.00, "Prod-A"),
                new Product("2", "B", 2.00, "Prod-B"));
        when(productService.getAllProduct()).thenReturn(product);
        String expectedJSON = new String(Files.readAllBytes(
                Paths.get("src/test/resources/allProductResponse.json")));
        mockMvc.perform(get("/api/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void testUpdateProduct() throws  Exception{
        Product product = new Product("1", "Z", 1.00, "descZ");
        when(productService.updateProduct("1",product)).thenReturn(product);
        String productJSON = new String(Files.readAllBytes(Paths.get("src/test/resources/updatedProductRequest.json")));
        String expectedJSON = new String(Files.readAllBytes(Paths.get("src/test/resources/updatedProductResponse.json")));

        mockMvc.perform(put("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }
}
