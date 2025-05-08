package com.musinsa.service;

import com.musinsa.domain.*;
import com.musinsa.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Brand brandA;

    @BeforeEach
    void setup() {
        brandA = Brand.builder().name("A").build();
    }

    @Test
    void getLowestPricePerCategory_returnsCorrectResult() {
        Product top = Product.builder().category(Category.TOP).price(10000).brand(brandA).build();

        when(productRepository.findByCategoryOrderByPriceAsc(Category.TOP)).thenReturn(List.of(top));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.OUTER)).thenReturn(List.of(
                Product.builder().category(Category.OUTER).price(5000).brand(brandA).build()
        ));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.PANTS)).thenReturn(List.of(
                Product.builder().category(Category.PANTS).price(3000).brand(brandA).build()
        ));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.SNEAKERS)).thenReturn(List.of(
                Product.builder().category(Category.SNEAKERS).price(9000).brand(brandA).build()
        ));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.BAG)).thenReturn(List.of(
                Product.builder().category(Category.BAG).price(2000).brand(brandA).build()
        ));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.HAT)).thenReturn(List.of(
                Product.builder().category(Category.HAT).price(1500).brand(brandA).build()
        ));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.SOCKS)).thenReturn(List.of(
                Product.builder().category(Category.SOCKS).price(1000).brand(brandA).build()
        ));
        when(productRepository.findByCategoryOrderByPriceAsc(Category.ACCESSORY)).thenReturn(List.of(
                Product.builder().category(Category.ACCESSORY).price(1200).brand(brandA).build()
        ));

        Map<String, Object> result = productService.getLowestPricePerCategory();

        assertThat(result).containsKey("총액");
        assertThat((Integer) result.get("총액")).isEqualTo(10000 + 5000 + 3000 + 9000 + 2000 + 1500 + 1000 + 1200);
        /* 실패 기대값 입력시
        assertThat(result).isNotNull();
        assertThat(result.get("총액")).isEqualTo(34500);
         */
        assertThat(result).containsKey("상의");
        assertThat(((Map<?, ?>) result.get("상의")).get("브랜드")).isEqualTo("A");
    }
}
