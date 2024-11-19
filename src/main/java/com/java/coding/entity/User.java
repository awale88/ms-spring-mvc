package com.java.coding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    @NonNull
    private String password;
}
