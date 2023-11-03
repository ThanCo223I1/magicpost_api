package com.magicpost.service;

import com.magicpost.model.Orders;
import com.paypal.api.payments.Order;

import java.util.List;

public interface IOrderService {
    Orders createOrders(Orders orders);
    List<Orders> getAll();
}
