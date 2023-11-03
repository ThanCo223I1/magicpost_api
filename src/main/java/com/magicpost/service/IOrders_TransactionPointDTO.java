package com.magicpost.service;

import com.magicpost.model.Account;
import com.magicpost.model.Orders;
import com.magicpost.model.dto.Orders_TransactionPointDTO;

import java.util.List;

public interface IOrders_TransactionPointDTO {
    List<Orders_TransactionPointDTO> findByTransactionPointId(Account account);
    void save(Orders orders);
}
