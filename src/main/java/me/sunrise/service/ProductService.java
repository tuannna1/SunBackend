package me.sunrise.service;


import me.sunrise.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {


    Page<ProductInfo>under25(Pageable pageable);

    Page<ProductInfo>from25to50(Pageable pageable);

    Page<ProductInfo>from50to100(Pageable pageable);

    Page<ProductInfo>from100to200(Pageable pageable);

    Page<ProductInfo>above200(Pageable pageable);

    ProductInfo findOne(String productId);

    // All selling products
    Page<ProductInfo> findUpAll(Pageable pageable);
    // All products
    Page<ProductInfo> findAll(Pageable pageable);
    // All products in a category
    Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable);

    // increase stock
    void increaseStock(String productId, int amount);

    //decrease stock
    void decreaseStock(String productId, int amount);

    ProductInfo offSale(String productId);

    ProductInfo onSale(String productId);

    ProductInfo update(ProductInfo productInfo);
    
    ProductInfo save(ProductInfo productInfo);

    void delete(String productId);


}
