package me.sunrise.service;

import me.sunrise.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

//    List<ProductCategory> findAll();
//
Page<ProductCategory> findAll(Pageable pageable);


    ProductCategory findOne(Integer categoryId);

    ProductCategory findByCategoryType(Integer categoryType);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory update(ProductCategory category);

    ProductCategory save(ProductCategory category);

    void delete(Integer categoryId);


}
