package com.magicpost.service;

import com.magicpost.model.Orders;
import com.magicpost.model.dto.OrdersDTO;
import com.paypal.api.payments.Order;

import java.util.List;

public interface IOrderService {
    Orders createOrders(Orders orders, long idEmployee);

    List<Orders> getAll();

    List<OrdersDTO> findAllByTransactionPoint_Id(long id);

    List<OrdersDTO> findAllByConsolidationPoint_Id(long id);

    OrdersDTO findById(long id);

    List<Object[]> getReceivedOrdersByConsolidationPoint();

    List<Object[]> getSentOrdersByTransactionPoint();
    void deleteOrder(long id);

    List<Object[]> getIncoming(int month, int year);
    List<Object[]> getOutgoing(int month, int year);
    List<Orders> getOrdersByMonthAndYear(int month, int year);

}
