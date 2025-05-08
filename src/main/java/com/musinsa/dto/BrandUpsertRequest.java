package com.musinsa.dto;

import lombok.*;

@Getter
@Setter
public class BrandUpsertRequest {
    private String oldBrandName;
    private String newBrandName;
}
