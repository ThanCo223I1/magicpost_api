package com.magicpost.repo;

import com.magicpost.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Long> {
    List<Orders> findAllByTransactionPoint_Id(long id);
    @Query("SELECT  MONTH(o.createOrder) AS month, YEAR(o.createOrder) AS year, COUNT(o.id) AS received_orders " +
            "FROM ConsolidationPoint cp " +
            "INNER JOIN orders_consolidation_points ocp ON cp.id = ocp.consolidationPoint.id " +
            "INNER JOIN Orders o ON ocp.order.id = o.id " +
            "GROUP BY  MONTH(o.createOrder), YEAR(o.createOrder) " +
            "ORDER BY YEAR(o.createOrder), MONTH(o.createOrder)")
    List<Object[]> getReceivedOrdersByConsolidationPoint();
    @Query("SELECT tp.name, MONTH(o.createOrder) AS month, YEAR(o.createOrder) AS year, COUNT(o.id) AS sent_orders " +
            "FROM TransactionPoint tp " +
            "LEFT JOIN Orders o ON tp.id = o.transactionPoint.id " +
            "GROUP BY tp.name, MONTH(o.createOrder), YEAR(o.createOrder) " +
            "ORDER BY YEAR(o.createOrder), MONTH(o.createOrder)")
    List<Object[]> getSentOrdersByTransactionPoint();

        @Query("SELECT  MONTH(o.createOrder) AS month, YEAR(o.createOrder) AS year, COUNT(o.id) AS received_orders " +
                "FROM ConsolidationPoint cp " +
                "INNER JOIN orders_consolidation_points ocp ON cp.id = ocp.consolidationPoint.id " +
                "INNER JOIN Orders o ON ocp.order.id = o.id " +
                "WHERE MONTH(o.createOrder) =:month AND YEAR(o.createOrder) =:year "+
                "GROUP BY  MONTH(o.createOrder), YEAR(o.createOrder) " +
                "ORDER BY YEAR(o.createOrder), MONTH(o.createOrder)")
    List<Object[]> getIncoming(@Param("month")int month,@Param("year") int year);
    @Query("SELECT tp.name, MONTH(o.createOrder) AS month, YEAR(o.createOrder) AS year, COUNT(o.id) AS sent_orders " +
            "FROM TransactionPoint tp " +
            "LEFT JOIN Orders o ON tp.id = o.transactionPoint.id " +
            "WHERE MONTH(o.createOrder) =:month AND YEAR(o.createOrder) =:year "+
            "GROUP BY tp.name, MONTH(o.createOrder), YEAR(o.createOrder) " +
            "ORDER BY YEAR(o.createOrder), MONTH(o.createOrder)")
    List<Object[]> getOutgoing(@Param("month")int month,@Param("year") int year);

    @Query("SELECT o FROM Orders o " +
            "WHERE FUNCTION('YEAR', o.createOrder) = :year " +
            "AND FUNCTION('MONTH', o.createOrder) = :month")
    List<Orders> getOrdersByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
