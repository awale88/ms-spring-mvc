package com.java.coding.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Builder
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    @NotBlank
    @Size
    private String name;
    @NotNull
    @Min(value = 0)
    private double price;
    @Size(max = 500)
    private String description;
}
