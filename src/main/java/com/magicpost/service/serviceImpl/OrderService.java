package com.magicpost.service.serviceImpl;

import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Employee;
import com.magicpost.model.Orders;
import com.magicpost.model.dto.OrdersDTO;
import com.magicpost.model.TransactionPoint;
import com.magicpost.repo.IEmployeeRepo;
import com.magicpost.repo.IOrderRepo;
import com.magicpost.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepo iOrderRepo;

    @Autowired
    private IEmployeeRepo iEmployeeRepo;
    @Autowired
    private IConsolidationPoint iConsolidationPoint;
    @Autowired
    private ITransactionPoint iTransactionPoint;

    @Override
    public Orders createOrders(Orders orders,long idEmployee) {
        Employee employeeTransaction=iEmployeeRepo.findEmployeeTransaction(idEmployee).orElse(null);
        if(employeeTransaction==null){
            long idConsolidationPoint=iConsolidationPoint.findIdConsolidationByEmployee(idEmployee);
            ConsolidationPoint consolidationPoint=iConsolidationPoint.findById(idConsolidationPoint);
            orders.getConsolidationPoints().add(consolidationPoint);
        }else {
            long idTransactionPoint=iTransactionPoint.findIdTransactionByIdEmployee(idEmployee);
            TransactionPoint transactionPoint=iTransactionPoint.findById(idTransactionPoint).get();
            orders.setTransactionPoint(transactionPoint);
        }
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

    @Override
    public List<Object[]> getReceivedOrdersByConsolidationPoint() {
        List<Object[]> results = iOrderRepo.getReceivedOrdersByConsolidationPoint();
        return results;    }

    @Override
    public List<Object[]> getIncoming(int month,int year) {
        List<Object[]> results = iOrderRepo.getIncoming(month, year);

        return results;
    }

    @Override
    public List<Object[]> getOutgoing(int month, int year) {
        List<Object[]> results = iOrderRepo.getOutgoing(month, year);

        return results;
    }

    @Override
    public List<Orders> getOrdersByMonthAndYear(int month, int year) {
        List<Orders> ordersList = iOrderRepo.getOrdersByMonthAndYear(month, year);
        return ordersList;
    }

    public List<Object[]> getSentOrdersByTransactionPoint() {
        List<Object[]> results = iOrderRepo.getSentOrdersByTransactionPoint();
        return results;
    }

}
