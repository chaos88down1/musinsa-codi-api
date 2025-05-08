package com.musinsa.service;

import com.musinsa.domain.Brand;
import com.musinsa.domain.Category;
import com.musinsa.domain.Product;
import com.musinsa.dto.ProductUpsertRequest;
import com.musinsa.repository.BrandRepository;
import com.musinsa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public Map<String, Object> getLowestPricePerCategory() {
        // 응답 JSON 을 만족하기 위해 key, value 구분해서 result 생성
        Map<String, Object> result = new LinkedHashMap<>();
        int total = 0;

        // 카테고리 별 최저가 상품 조회
        for (Category category : Category.values()) {
            List<Product> list = productRepository.findByCategoryOrderByPriceAsc(category);
            if (list.isEmpty()) {
                throw new NoSuchElementException("상품이 존재하지 않는 카테고리입니다: " + category.name());
            }

            Product lowest = list.get(0);

            Map<String, Object> value = new LinkedHashMap<>();
            value.put("브랜드", lowest.getBrand().getName());
            value.put("가격", lowest.getPrice());

            result.put(category.getKorean(), value);
            total += lowest.getPrice();
        }

        result.put("총액", total);
        return result;
    }

    public Map<String, Object> getLowestTotalBrand() {
        Map<String, Object> response = new LinkedHashMap<>();

        // 모든 브랜드 이름 목록 조회
        Set<String> brandNames = productRepository.findAll().stream()
                .map(p -> p.getBrand().getName())
                .collect(Collectors.toSet());

        String lowestBrandName = null;
        int lowestTotal = Integer.MAX_VALUE;
        List<Map<String, Object>> lowestItems = null;

        for (String brandName : brandNames) {
            List<Product> products = productRepository.findByBrandName(brandName);

            // 이 브랜드가 모든 카테고리의 상품을 가지고 있는지 확인
            if (products.size() != Category.values().length) continue;

            int total = 0;
            List<Map<String, Object>> items = new ArrayList<>();

            for (Category category : Category.values()) {
                Product product = products.stream()
                        .filter(p -> p.getCategory() == category)
                        .findFirst()
                        .orElse(null);

                // 카테고리가 중복된 경우가 있을 수 있으므로 한번 더 체크
                if (product == null) {
                    total = Integer.MAX_VALUE;
                    break;
                }

                Map<String, Object> item = new LinkedHashMap<>();
                item.put("카테고리", category.getKorean());
                item.put("가격", product.getPrice());
                items.add(item);

                total += product.getPrice();
            }

            if (total < lowestTotal) {
                lowestTotal = total;
                lowestBrandName = brandName;
                lowestItems = items;
            }
        }

        if (lowestBrandName == null) {
            throw new IllegalStateException("모든 카테고리를 포함한 브랜드가 없습니다.");
        }

        Map<String, Object> lowest = new LinkedHashMap<>();
        lowest.put("브랜드", lowestBrandName);
        lowest.put("카테고리", lowestItems);
        lowest.put("총액", lowestTotal);

        response.put("최저가", lowest);
        return response;
    }

    public Map<String, Object> getMinMaxPriceByCategory(String categoryKorean) {
        // 한글로 입력받은 카테고리명을 enum 으로 변환
        Category category = Arrays.stream(Category.values())
                .filter(c -> c.getKorean().equals(categoryKorean))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리명입니다: " + categoryKorean));

        List<Product> products = productRepository.findByCategory(category);

        if (products.isEmpty()) {
            throw new NoSuchElementException("해당 카테고리에는 상품이 없습니다: " + categoryKorean);
        }

        // 최저가, 최고가 계산
        int minPrice = products.stream().mapToInt(Product::getPrice).min().getAsInt();
        int maxPrice = products.stream().mapToInt(Product::getPrice).max().getAsInt();

        // 최저가, 최고가 상품의 브랜드가 여러개일 수 있으므로
        List<Map<String, Object>> minBrands = new ArrayList<>();
        List<Map<String, Object>> maxBrands = new ArrayList<>();

        for (Product p : products) {
            if (p.getPrice() == minPrice) {
                minBrands.add(Map.of("브랜드", p.getBrand().getName(), "가격", p.getPrice()));
            }
            if (p.getPrice() == maxPrice) {
                maxBrands.add(Map.of("브랜드", p.getBrand().getName(), "가격", p.getPrice()));
            }
        }

        return Map.of(
                "카테고리", categoryKorean,
                "최저가", minBrands,
                "최고가", maxBrands
        );
    }

    public String upsertProduct(ProductUpsertRequest request) {
        // 한글로 입력받은 카테고리명을 enum 으로 변환
        Category category = Arrays.stream(Category.values())
                .filter(c -> c.getKorean().equals(request.getCategoryName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리: " + request.getCategoryName()));

        // 브랜드 조회 (없으면 예외)
        Brand brand = brandRepository.findByName(request.getBrandName())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 브랜드입니다: " + request.getBrandName()));

        // 기존 상품 조회
        Product existing = productRepository.findByBrandNameAndCategory(brand.getName(), category);

        if (existing != null) {
            // update
            existing = Product.builder()
                    .id(existing.getId())
                    .category(category)
                    .price(request.getPrice())
                    .brand(brand)
                    .build();
            productRepository.save(existing);
            return "상품 수정 성공";
        } else {
            // insert
            existing = Product.builder()
                    .category(category)
                    .price(request.getPrice())
                    .brand(brand)
                    .build();
            productRepository.save(existing);
            return "상품 등록 성공";
        }
    }

    public void deleteProduct(String brandName, String categoryKorean) {
        // 한글로 입력받은 카테고리명을 enum 으로 변환
        Category category = Arrays.stream(Category.values())
                .filter(c -> c.getKorean().equals(categoryKorean.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리: " + categoryKorean));

        // 브랜드 조회
        Brand brand = brandRepository.findByName(brandName.trim())
                .orElseThrow(() -> new NoSuchElementException("브랜드가 존재하지 않습니다: " + brandName));

        // 해당 브랜드/카테고리 상품 존재 여부 확인
        Product product = productRepository.findByBrandAndCategory(brand, category);
        if (product == null) {
            throw new NoSuchElementException("해당 브랜드의 상품이 존재하지 않습니다.");
        }

        productRepository.delete(product);
    }


}
