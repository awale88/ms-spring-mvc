package com.java.coding.response;

import com.java.coding.enums.Message;
import com.java.coding.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse <T>{

    private String message;
    private Status status;
    private T data;

    public ProductResponse(Status status, Message message, T data, Object... args) {
        this.status = status;
        this.message = message.format(args);
        this.data = data;
    }
}
