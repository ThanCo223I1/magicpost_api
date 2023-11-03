package com.magicpost.controller.orders;

import com.magicpost.model.Account;
import com.magicpost.model.Orders;
import com.magicpost.model.dto.Orders_ConsolidationPointDTO;
import com.magicpost.model.dto.Orders_TransactionPointDTO;
import com.magicpost.service.IAccount;
import com.magicpost.service.IOrders_ConsolidationPointDTO;
import com.magicpost.service.IOrders_TransactionPointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrdersController2 {
    @Autowired
    IAccount iAccount;
    @Autowired
    IOrders_ConsolidationPointDTO iOrders_consolidationPointDTO;
    @Autowired
    IOrders_TransactionPointDTO iOrdersTransactionPointDTO;

    @GetMapping("/consolidationPoint/account/{accountId}")
    public List<Orders_ConsolidationPointDTO> order_consolidationPoint(@PathVariable long accountId) {
        Account account = iAccount.findAccountById(accountId);
        return iOrders_consolidationPointDTO.findAllOrder_ConsolidationPoint(account);
    }

    @GetMapping("/transactionPoint/account/{accountId}")
    public List<Orders_TransactionPointDTO> order_transactionPoint(@PathVariable long accountId) {
        Account account = iAccount.findAccountById(accountId);
        return iOrdersTransactionPointDTO.findByTransactionPointId(account);
    }

    @PostMapping("/save")
    public void save(@RequestBody Orders orders) {
        iOrders_consolidationPointDTO.save(orders);
    }
}
