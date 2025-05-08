package com.musinsa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpsertRequest {
    private String brandName;
    private String categoryName;
    private int price;
}
