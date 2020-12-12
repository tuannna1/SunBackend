package me.sunrise.service;

import me.sunrise.entity.Cart;
import me.sunrise.entity.ProductInOrder;
import me.sunrise.entity.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
