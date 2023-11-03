package com.magicpost.repo;

import com.magicpost.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Long> {
    List<Orders> findAllByTransactionPoint_Id(long id);
    List<Orders> findAllByConsolidationPoint_Id(long id);
}
