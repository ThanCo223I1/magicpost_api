package com.magicpost.repo;

import com.magicpost.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrdersRepo extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.consolidationPoint.id = :consolidationPointId ORDER BY o.status.nameStatus DESC")
    List<Orders> findByConsolidationPointId(@Param("consolidationPointId") Long consolidationPointId);

    @Query("SELECT o FROM Orders o WHERE o.transactionPoint.id = :transactionPointId ORDER BY o.status.nameStatus DESC")
    List<Orders> findByTransactionPointId(@Param("transactionPointId") Long transactionPointId);
}
