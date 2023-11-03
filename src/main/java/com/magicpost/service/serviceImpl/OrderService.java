package com.magicpost.service.serviceImpl;

import com.magicpost.model.Orders;
import com.magicpost.repo.IOrderRepo;
import com.magicpost.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepo iOrderRepo;
    @Override
    public Orders createOrders(Orders orders) {
        return iOrderRepo.save(orders) ;
    }

    @Override
    public List<Orders> getAll() {
        return iOrderRepo.findAll();
    }
}
