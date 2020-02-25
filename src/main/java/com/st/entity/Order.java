package com.st.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private Long id;
    private Long idBasket;
    private String phone;
    private String address;
    private String description;
    private boolean action;
    private Timestamp create_at;
    private Timestamp close_at;
}
