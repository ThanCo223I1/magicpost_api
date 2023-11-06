package com.magicpost.repo;

import com.magicpost.model.Orders;
import com.magicpost.model.TransactionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrdersRepo extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.consolidationPoint.id = :consolidationPointId ORDER BY o.status.nameStatus DESC")
    List<Orders> findByConsolidationPointId(@Param("consolidationPointId") Long consolidationPointId);

    @Query("SELECT o FROM Orders o WHERE o.transactionPoint.id = :transactionPointId ORDER BY o.status.nameStatus DESC, o.id DESC")
    List<Orders> findByTransactionPointId(@Param("transactionPointId") Long transactionPointId);

    @Query(nativeQuery = true, value = "select distinct o.* " +
            "from orders o " +
            "join orders_consolidation_points ocp on ocp.orders_id = o.id " +
            "join consolidation_point cp on cp.id = ocp.consolidation_points_id " +
            "join consolidation_point_employee cpe on cpe.consolidation_point_id = cp.id " +
            "join employee e on e.id = cpe.employee_id " +
            "join account a on a.id = e.account_id " +
            "where a.id = :accountId " +
            "order by o.id desc;")
    List<Orders> findByConsolidationPoints_Employee_IdAccount(@Param("accountId") long accountId);
}
