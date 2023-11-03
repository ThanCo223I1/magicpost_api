package com.magicpost.service.serviceImpl;

import com.magicpost.model.Orders;
import com.magicpost.model.dto.OrdersDTO;
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
        return iOrderRepo.save(orders);
    }

    @Override
    public List<Orders> getAll() {
        return iOrderRepo.findAll();
    }

    @Override
    public List<OrdersDTO> findAllByTransactionPoint_Id(long id) {
        List<Orders> orders = iOrderRepo.findAllByTransactionPoint_Id(id);

        return null;
    }

    @Override
    public List<OrdersDTO> findAllByConsolidationPoint_Id(long id) {
        return null;
    }

    @Override
    public OrdersDTO findById(long id) {
        try {
            OrdersDTO ordersDTO = iOrderRepo.findById(id).get().orderDTO();
            return ordersDTO;
        } catch (Exception e) {
            return null;
        }

    }
}
