package com.java.coding.service;

import com.java.coding.entity.Product;
import com.java.coding.exception.ProductNotFoundException;
import com.java.coding.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProduct() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            logger.info("NO Product found in Database");
        } else {
            logger.info("Retrieved [{}] products from the database", products.size());
        }
        return products;
    }

    public Optional<Product> getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresentOrElse(
                p -> logger.info("Retrieving Product with ID: [{}]", id),
                () -> logger.warn("Product NOT found with ID: [{}]", id)
        );
        return product;
    }

    public Product createProduct(Product product) {
        Product createdProduct = productRepository.save(product);
        logger.info("Product created with ID: [{}]", createdProduct.getId());
        return createdProduct;
    }

    public Product updateProduct(String id, Product upDatedProduct) {

        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            upDatedProduct.setId(id);
            Product saveProduct = productRepository.save(upDatedProduct);
            logger.info("Updated existing Product with ID: [{}]", id);
            return saveProduct;
        } else {
            logger.warn("Failed to update product with ID: [{}]", id);
            throw new ProductNotFoundException(id);
        }
    }

    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Deleted product with ID: [{}]", id);
        } else {
            logger.warn("Failed to delete Product with ID: [{}]", id);
            throw new ProductNotFoundException(id);
        }
    }
}
