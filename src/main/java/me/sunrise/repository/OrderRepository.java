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
    Page<OrderMain> getOrderStatus1(Pageable pageable);

    @Query("SELECT m FROM OrderMain m WHERE m.orderStatus = 2 ")
    Page<OrderMain> getOrderStatus2(Pageable pageable);

    @Query("SELECT m FROM OrderMain m WHERE m.orderStatus = 3 ")
    Page<OrderMain> getOrderStatus3(Pageable pageable);

    @Query("SELECT m FROM OrderMain m WHERE m.orderStatus = 0")
    Page<OrderMain> getOrderStatus0(Pageable pageable);

    @Query("SELECT new map(count(m.orderId) as SOLUONG0) FROM OrderMain m WHERE m.orderStatus = 0")
    Object[] getcountStatus0();
    @Query("SELECT new map(count(m.orderId) as SOLUONG1) FROM OrderMain m WHERE m.orderStatus = 1")
    Object[] getcountStatus1();
    @Query("SELECT new map(count(m.orderId) as SOLUONG2) FROM OrderMain m WHERE m.orderStatus = 2")
    Object[] getcountStatus2();
    @Query("SELECT new map(count(m.orderId) as SOLUONG3) FROM OrderMain m WHERE m.orderStatus = 3")
    Object[] getcountStatus3();
    @Query("SELECT new map(count(m.orderId) as SOLUONG) FROM OrderMain m ")
    Object[] getcountAll();
    @Query("SELECT new map(sum(m.orderAmount) as TONG0) FROM OrderMain m WHERE m.orderStatus = 0")
    Object[] getsumStatus0();
    @Query("SELECT new map(sum(m.orderAmount) as TONG1) FROM OrderMain m WHERE m.orderStatus = 1")
    Object[] getsumStatus1();
    @Query("SELECT new map(sum(m.orderAmount) as TONG2) FROM OrderMain m WHERE m.orderStatus = 2")
    Object[] getsumStatus2();
    @Query("SELECT new map(sum(m.orderAmount) as TONG3) FROM OrderMain m WHERE m.orderStatus = 3")
    Object[] getsumStatus3();
    @Query("SELECT new map(sum(m.orderAmount) as TONG) FROM OrderMain m WHERE m.orderStatus <> 2 ")
    Object[] getsumAll();



}

