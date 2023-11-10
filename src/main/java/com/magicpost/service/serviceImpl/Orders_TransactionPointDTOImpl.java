package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.Orders;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.Orders_TransactionPointDTO;
import com.magicpost.repo.IOrderTypeRepo;
import com.magicpost.repo.IOrdersRepo;
import com.magicpost.repo.ITransactionPointRepo;
import com.magicpost.service.IOrders_TransactionPointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Orders_TransactionPointDTOImpl implements IOrders_TransactionPointDTO {
    @Autowired
    IOrdersRepo iOrdersRepo;
    @Autowired
    IOrderTypeRepo iOrderTypeRepo;
    @Autowired
    ITransactionPointRepo iTransactionPointRepo;

    @Override
    public List<Orders_TransactionPointDTO> findByTransactionPointId(Account account) {
        List<Orders_TransactionPointDTO> orders_transactionPointDTOList = new ArrayList<>();
        TransactionPoint transactionPoint = iTransactionPointRepo.findTransactionPointsByAccountId(account.getId());
        Orders_TransactionPointDTO orders_transactionPointDTO;
        long count = 0;
        for (Orders orders : iOrdersRepo.findByTransactionPointId(transactionPoint.getId())) {
            orders_transactionPointDTO = new Orders_TransactionPointDTO(count++, orders, iOrderTypeRepo.findTypesByOrderId(orders.getId()));
            orders_transactionPointDTOList.add(orders_transactionPointDTO);
        }
        return orders_transactionPointDTOList;
    }

    @Override
    public void save(Orders orders) {
        iOrdersRepo.save(orders);
    }
}
