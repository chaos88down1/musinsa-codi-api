package com.musinsa.controller;

import com.musinsa.dto.BrandDeleteRequest;
import com.musinsa.dto.BrandUpsertRequest;
import com.musinsa.repository.BrandRepository;
import com.musinsa.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final BrandRepository brandRepository;

    /**
     * 구현 4 - 브랜드 추가 / 업데이트 / 삭제 하는 API
     */
    @PostMapping("/upsert")
    public ResponseEntity<?> upsertBrand(@RequestBody BrandUpsertRequest request) {
        try {
            String result = brandService.upsertBrand(request);
            return ResponseEntity.ok(Map.of("result", result, "name", request.getNewBrandName()));
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "브랜드 등록/수정 실패", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBrand(@RequestBody BrandDeleteRequest request) {
        try {
            if (request.getBrandName() == null || request.getBrandName().isBlank()) {
                return ResponseEntity.badRequest().body(
                        Map.of("error", "브랜드 삭제 실패", "message", "브랜드 이름은 필수입니다.")
                );
            }

            brandService.deleteBrand(request.getBrandName());
            return ResponseEntity.ok(Map.of("result", "브랜드 및 상품 삭제 성공"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "브랜드 삭제 실패", "message", e.getMessage())
            );
        }
    }

}
