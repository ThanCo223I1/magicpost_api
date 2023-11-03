package com.magicpost.service;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Orders;
import com.magicpost.model.dto.Orders_ConsolidationPointDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrders_ConsolidationPointDTO {
    List<Orders_ConsolidationPointDTO> findAllOrder_ConsolidationPoint(Account account);
    void save(Orders orders);
}
