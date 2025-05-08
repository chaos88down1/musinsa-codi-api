package com.musinsa.repository;

import com.musinsa.domain.Brand;
import com.musinsa.domain.Category;
import com.musinsa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryOrderByPriceAsc(Category category);

    List<Product> findByBrandName(String brandName);

    List<Product> findByCategory(Category category);

    Product findByBrandNameAndCategory(String brandName, Category category);

    Product findByBrandAndCategory(Brand brand, Category category);

    List<Product> findByBrand(Brand brand);
}
