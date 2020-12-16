package me.sunrise.repository;


import me.sunrise.entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderMain, Integer> {
    OrderMain findByOrderId(Long orderId);


    Page<OrderMain> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);


    Page<OrderMain> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail, Pageable pageable);

    Page<OrderMain> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

    Page<OrderMain> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(String buyerPhone, Pageable pageable);

//    Page<OrderMain> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);
    @Query("SELECT m FROM OrderMain m WHERE m.orderStatus = 1 ")
    Page<OrderMain>getOrderStatus1(Pageable pageable);
    @Query("SELECT m FROM OrderMain m WHERE m.orderStatus = 2 ")
    Page<OrderMain>getOrderStatus2(Pageable pageable);
    @Query("SELECT m FROM OrderMain m WHERE m.orderStatus = 3 ")
    Page<OrderMain>getOrderStatus3(Pageable pageable);

}
