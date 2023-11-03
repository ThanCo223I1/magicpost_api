package com.magicpost.controller.Account.OrdersController;

import com.magicpost.model.Orders;
import com.magicpost.repo.IOrderRepo;
import com.magicpost.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrderService iOrderService;
    @PostMapping("/createOrder")
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders){
        return new ResponseEntity<>(iOrderService.createOrders(orders),HttpStatus.OK);
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity <List<Orders>> getAll(){
        List<Orders> ordersList = iOrderService.getAll();
        return new ResponseEntity<>(ordersList,HttpStatus.OK);
    }
}
