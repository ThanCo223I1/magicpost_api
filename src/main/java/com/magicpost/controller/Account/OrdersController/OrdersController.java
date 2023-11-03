package com.magicpost.controller.Account.OrdersController;

import com.magicpost.model.Orders;
import com.magicpost.model.dto.OrdersDTO;
import com.magicpost.repo.IOrderRepo;
import com.magicpost.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrderService iOrderService;
    @PostMapping("/createOrder/{idEmployee}")
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders,@PathVariable long idEmployee){
        return new ResponseEntity<>(iOrderService.createOrders(orders,idEmployee),HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrdersDTO>> getAll() {
        List<Orders> ordersList = iOrderService.getAll();
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        for (Orders o : ordersList) {
            ordersDTOS.add(o.orderDTO());
        }
        return new ResponseEntity<>(ordersDTOS, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        OrdersDTO ordersDTO = iOrderService.findById(id);
        if (ordersDTO != null)
            return ResponseEntity.ok(ordersDTO);
        return ResponseEntity.ok("not found");
    }
}
