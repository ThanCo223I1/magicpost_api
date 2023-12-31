package com.magicpost.controller.orders;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Orders;
import com.magicpost.model.dto.Orders_ConsolidationPointDTO;
import com.magicpost.model.dto.Orders_TransactionPointDTO;
import com.magicpost.repo.IOrderRepo;
import com.magicpost.service.IAccount;
import com.magicpost.service.IOrderService;
import com.magicpost.service.IOrders_ConsolidationPointDTO;
import com.magicpost.service.IOrders_TransactionPointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    IOrderService iOrderService;

    @GetMapping("/consolidationPoint/account/{accountId}")
    public List<Orders_ConsolidationPointDTO> order_consolidationPoint(@PathVariable long accountId) {
        return iOrders_consolidationPointDTO.findByConsolidationPoints_Employee_IdAccount(accountId);
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

    @GetMapping("/getAllOrder")
    public ResponseEntity<List<Orders>> getAllOrders(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity.ok(iOrderService.getOrdersByMonthAndYear(month, year));
    }

    @GetMapping("getReceivedOrdersByConsolidationPoint")
    public ResponseEntity<?> getReceivedOrdersByConsolidationPoint(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity.ok(iOrderService.getIncoming(month, year));
    }

    @GetMapping("getSentOrdersByTransactionPoint")
    public ResponseEntity<?> getSentOrdersByTransactionPoint(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity.ok(iOrderService.getOutgoing(month, year));
    }

    @PostMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable long id) {
        iOrderService.deleteOrder(id);
    }

    @PostMapping("/{idOrder}/sendTo_ConsolidationPoint")
    public Orders sendTo_ConsolidationPoint(@PathVariable long idOrder, @RequestBody ConsolidationPoint consolidationPoint) {
        return iOrders_consolidationPointDTO.sendOrder(idOrder, consolidationPoint);
    }

    @GetMapping("/{idOrder}/findAllByNotInAccountId_AndNotConsolExistOrder/{idAccount}")
    public List<ConsolidationPoint> findAllByNotInAccountId_AndNotConsolExistOrder(@PathVariable long idOrder, @PathVariable long idAccount) {
        return iOrders_consolidationPointDTO.findAllByNotInAccountId_AndNotConsolExistOrder(idAccount, idOrder);
    }
}
