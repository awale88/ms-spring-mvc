package com.java.coding.controller;

import com.java.coding.entity.Product;
import com.java.coding.enums.Message;
import com.java.coding.enums.Status;
import com.java.coding.exception.ProductNotFoundException;
import com.java.coding.response.ProductResponse;
import com.java.coding.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(new
                ProductResponse<>(Message.PRODUCTS_RETRIEVED.getMessage(), Status.SUCCESS, products));
    }

    @GetMapping("/{id}")
    public ProductResponse<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> new ProductResponse<>(Message.PRODUCT_FOUND.getMessage(), Status.SUCCESS, value))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ProductResponse<>(Message.PRODUCT_CREATED.getMessage(), Status.CREATED, createdProduct);
    }

    @PutMapping("/{id}")
    public ProductResponse<Product> updateProduct(@PathVariable String id, @Valid @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(id, updatedProduct);
        return new ProductResponse<>(Message.PRODUCT_UPDATED.getMessage(), Status.UPDATED, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ProductResponse<>(Message.PRODUCT_DELETED.getMessage(), Status.DELETED, null);
    }
}
