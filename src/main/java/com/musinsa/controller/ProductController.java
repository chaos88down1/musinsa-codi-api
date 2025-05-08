package com.musinsa.controller;

import com.musinsa.dto.ProductDeleteRequest;
import com.musinsa.dto.ProductUpsertRequest;
import com.musinsa.repository.BrandRepository;
import com.musinsa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final BrandRepository brandRepository;
    private final ProductService productService;

    /**
     * 구현 1 - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
     */
    @GetMapping("/lowest-price-per-category")
    public ResponseEntity<Map<String, Object>> getLowestPricePerCategory() {
        try {
            Map<String, Object> result = productService.getLowestPricePerCategory();
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "error", "카테고리 데이터 없음",
                            "message", e.getMessage()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of(
                            "error", "서버 오류",
                            "message", e.getMessage()
                    )
            );
        }
    }

    /**
     * 구현 2 - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
     */
    @GetMapping("/lowest-total-brand")
    public ResponseEntity<Map<String, Object>> getLowestTotalBrand() {
        try {
            Map<String, Object> result = productService.getLowestTotalBrand();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "error", "단일 브랜드 최저가 조회 실패",
                            "message", e.getMessage()
                    )
            );
        }
    }

    /**
     * 구현 3 - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
     */
    @GetMapping("/min-max-price-by-category")
    public ResponseEntity<Map<String, Object>> getMinMaxPriceByCategory(@RequestParam String category) {
        try {
            Map<String, Object> result = productService.getMinMaxPriceByCategory(category);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "error", "카테고리 조회 실패",
                            "message", e.getMessage()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Map.of(
                            "error", "서버 오류",
                            "message", e.getMessage()
                    )
            );
        }
    }

    /**
     * 구현 4 - 상품 추가 / 업데이트 / 삭제 하는 API
     */
    @PostMapping("/upsert")
    public ResponseEntity<?> upsertProduct(@RequestBody ProductUpsertRequest request) {
        try {
            String result = productService.upsertProduct(request);
            return ResponseEntity.ok(Map.of("result", result, "brandName", request.getBrandName(), "categoryName", request.getCategoryName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "상품 등록/수정 실패", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDeleteRequest request) {
        try {
            if (request.getBrandName() == null || request.getBrandName().isBlank() ||
                    request.getCategoryName() == null || request.getCategoryName().isBlank()) {
                return ResponseEntity.badRequest().body(
                        Map.of("error", "상품 삭제 실패", "message", "브랜드 및 카테고리 이름은 필수입니다.")
                );
            }

            productService.deleteProduct(request.getBrandName(), request.getCategoryName());
            return ResponseEntity.ok(Map.of("result", "브랜드 및 상품 삭제 성공"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "상품 삭제 실패", "message", e.getMessage())
            );
        }
    }

}
