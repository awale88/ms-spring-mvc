package com.java.coding.service;

import com.java.coding.entity.Product;
import com.java.coding.exception.ProductNotFoundException;
import com.java.coding.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = List.of(new Product("1", "Product1", 1, "P1"),
                new Product("2", "Product2", 2, "P2"));
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProduct();

        verify(logger, never()).info("Retrieved [{}] products from the database", mockProducts.size());
        assertEquals(mockProducts, result);
    }

    @Test
    void testUnableToGetAnyProduct() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<Product> result = productService.getAllProduct();

        verify(logger, never()).info("NO Product found in Database");
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateNewProduct() {
        Product product = new Product("1", "A", 1, "A");
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        verify(logger, never()).info("Product created with ID: [{}]", product.getId());
        assertEquals(product, createdProduct);
    }

    @Test
    void testFindProductById() {
        Product product = new Product("1", "A", 1, "A");
        when(productRepository.findById("1")).thenReturn((Optional.of(product)));

        Optional<Product> result = productService.getProductById("1");
        assertTrue(result.isPresent());
        //assertEquals(product, result.get());
        verify(productRepository, times(1)).findById(product.getId());
        verify(logger, never()).info("Retrieving Product with ID: [{}]", product.getId());
    }

    @Test
    void testUnableToFindProductById() {
        String productId = "2";
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById(productId);
        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(productId);
        verify(logger, never()).warn("Product NOT found with ID: [{}]", productId);

    }

    @Test
    void testFindProductForUpdate() {

        Product existingProduct = new Product("1", "Old", 1, "Old Product");
        Product updatedProduct = new Product("1", "New", 1, "Updated Product");

        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(existingProduct.getId(), updatedProduct);

        assertEquals(updatedProduct, result);
        verify(productRepository, times(1)).findById(existingProduct.getId());
        verify(logger, never()).info("Updated existing Product with ID: [{}]", updatedProduct.getId());

    }

    @Test
    void testUnableToFindProductForUpdate() {
        String productId = "100";
        Product updatedProduct = new Product("1", "New", 1, "Updated Product");
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(productId, updatedProduct));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(updatedProduct);
        verify(logger, never()).warn("Failed to update product with ID: [{}]", productId);
    }

    @Test
    void testDeleteProductById() {
        Product existingProduct = new Product("1", "Old", 1, "Old Product");
        when(productRepository.existsById(existingProduct.getId())).thenReturn(true);

        productService.deleteProduct(existingProduct.getId());

        verify(productRepository, times(1)).existsById(existingProduct.getId());
        verify(productRepository, times(1)).deleteById(existingProduct.getId());
        verify(logger, times(0)).info("Deleted product with ID: [{}]", existingProduct);
    }

    @Test
    void testUnableToDeleteProductById() {
        Product existingProduct = new Product("1", "Old", 1, "Old Product");
        when(productRepository.existsById(existingProduct.getId())).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(existingProduct.getId()));
        verify(productRepository, times(1)).existsById(existingProduct.getId());
        verify(productRepository, never()).deleteById(existingProduct.getId());
        verify(logger, never()).info("Deleted product with ID: [{}]", existingProduct);
    }
}
