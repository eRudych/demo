package com.st.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private Long id;
    private String title;
    private String description;
    private String text;
    private Timestamp date;
}