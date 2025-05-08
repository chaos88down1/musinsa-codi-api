package com.musinsa.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDeleteRequest {
    private String brandName;
    private String categoryName;
}
