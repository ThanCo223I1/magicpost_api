package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Orders;
import com.magicpost.model.dto.Orders_ConsolidationPointDTO;
import com.magicpost.repo.IConsolidationPointRepo;
import com.magicpost.repo.IOrderTypeRepo;
import com.magicpost.repo.IOrdersRepo;
import com.magicpost.service.IOrders_ConsolidationPointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Orders_ConsolidationPointDTOImpl implements IOrders_ConsolidationPointDTO {
    @Autowired
    IOrdersRepo iOrdersRepo;
    @Autowired
    IOrderTypeRepo iOrderTypeRepo;
    @Autowired
    IConsolidationPointRepo iConsolidationPointRepo;
    @Override
    public void save(Orders orders) {
        iOrdersRepo.save(orders);
    }

    @Override
    public List<Orders_ConsolidationPointDTO> findByConsolidationPoints_Employee_IdAccount(long accountId) {
        List<Orders_ConsolidationPointDTO> orders_consolidationPointDTOList = new ArrayList<>();
        Orders_ConsolidationPointDTO orders_consolidationPointDTO;
        long count = 0;
        for (Orders order : iOrdersRepo.findByConsolidationPoints_Employee_IdAccount(accountId)) {
            orders_consolidationPointDTO = new Orders_ConsolidationPointDTO(count++, order, iOrderTypeRepo.findTypesByOrderId(order.getId()));
            orders_consolidationPointDTOList.add(orders_consolidationPointDTO);
        }
        return orders_consolidationPointDTOList;
    }

    @Override
    public Orders sendOrder(long idOrder, ConsolidationPoint consolidationPoint) {
        Orders order = iOrdersRepo.findById(idOrder).get();

        List<ConsolidationPoint> uniqueConsolidationPoints = new ArrayList<>(order.getConsolidationPoints());

        uniqueConsolidationPoints.removeIf(cp -> cp.equals(consolidationPoint));

        uniqueConsolidationPoints.add(consolidationPoint);

        order.setConsolidationPoints(uniqueConsolidationPoints);

        return iOrdersRepo.save(order);
    }

    @Override
    public List<ConsolidationPoint> findAllByNotInAccountId_AndNotConsolExistOrder(long idAccount, long idOrder) {
        return iConsolidationPointRepo.findAllByNotInAccountId_AndNotConsolExistOrder(idAccount, idOrder);
    }
}
