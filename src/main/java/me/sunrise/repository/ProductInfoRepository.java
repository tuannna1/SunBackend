package me.sunrise.repository;

import me.sunrise.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    ProductInfo findByProductId(String id);
    // onsale product
    Page<ProductInfo> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category
    Page<ProductInfo> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<ProductInfo> findAllByOrderByProductId(Pageable pageable);
    @Query("SELECT m FROM ProductInfo m WHERE m.productPrice < 25  ")
    Page<ProductInfo>under25(Pageable pageable);
    @Query("SELECT m FROM ProductInfo m WHERE m.productPrice >= 25 and m.productPrice < 50  ")
    Page<ProductInfo>from25to50(Pageable pageable);
    @Query("SELECT m FROM ProductInfo m WHERE m.productPrice >= 50 and m.productPrice < 100 ")
    Page<ProductInfo>from50to100(Pageable pageable);
    @Query("SELECT m FROM ProductInfo m WHERE m.productPrice >= 100 and m.productPrice < 200  ")
    Page<ProductInfo>from100to200(Pageable pageable);
    @Query("SELECT m FROM ProductInfo m WHERE m.productPrice > 200  ")
    Page<ProductInfo>above200(Pageable pageable);
}
