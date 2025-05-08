package com.musinsa.service;

import com.musinsa.domain.Brand;
import com.musinsa.dto.BrandUpsertRequest;
import com.musinsa.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public String upsertBrand(BrandUpsertRequest request) {
        String oldName = request.getOldBrandName();
        String newName = request.getNewBrandName();

        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("새 브랜드 이름은 필수입니다.");
        }

        if (oldName != null && !oldName.isBlank()) {
            // 이름 수정
            Brand existing = brandRepository.findByName(oldName.trim())
                    .orElseThrow(() -> new NoSuchElementException("존재하지 않는 브랜드입니다: " + oldName));
            existing.setName(newName.trim());
            brandRepository.save(existing);
            return "브랜드 이름 수정 성공";
        } else {
            // 새 브랜드 등록
            Brand brand = Brand.builder().name(newName.trim()).build();
            brandRepository.save(brand);
            return "브랜드 등록 성공";
        }
    }

    public void deleteBrand(String brandName) {
        Brand brand = brandRepository.findByName(brandName.trim())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 브랜드입니다: " + brandName));

        // products도 함께 삭제
        brandRepository.delete(brand);
    }
}
