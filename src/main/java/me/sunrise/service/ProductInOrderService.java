package me.sunrise.service;

import me.sunrise.entity.ProductInOrder;
import me.sunrise.entity.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
