package com.st.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private Long id;
    private String mark;
    private String model;
    private String range;
    private String category;
    private String years;
    private String price;
    private String info;
    private String photo1;
    private String photo2;
    private String photo3;
}
