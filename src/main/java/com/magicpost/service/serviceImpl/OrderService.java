package com.magicpost.service.serviceImpl;

import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Employee;
import com.magicpost.model.Orders;
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
//        Employee employeeConsolidation=iEmployeeRepo.findEmployeeConsolidation(idEmployee).get();
        Employee employeeTransaction=iEmployeeRepo.findEmployeeTransaction(idEmployee).orElse(null);
        if(employeeTransaction==null){
            long idConsolidationPoint=iConsolidationPoint.findIdConsolidationByEmployee(idEmployee);
            ConsolidationPoint consolidationPoint=iConsolidationPoint.findById(idConsolidationPoint);
            orders.setConsolidationPoint(consolidationPoint);
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
}
