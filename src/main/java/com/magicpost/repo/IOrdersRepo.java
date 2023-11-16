package com.magicpost.repo;

import com.magicpost.model.Orders;
import com.magicpost.model.dto.DateEndOrder_YearDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;
import org.hibernate.transform.ResultTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.ArrayList;
import java.util.List;

public interface IOrdersRepo extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.transactionPoint.id = :transactionPointId ORDER BY FIELD(o.status.id, 6, 3, 4), o.id DESC")
    List<Orders> findByTransactionPointId(@Param("transactionPointId") Long transactionPointId);

    @Query(nativeQuery = true, value = "select distinct o.* " +
            "from orders o " +
            "join status s on s.id = o.status_id " +
            "join orders_consolidation_points ocp on ocp.orders_id = o.id " +
            "join consolidation_point cp on cp.id = ocp.consolidation_points_id " +
            "join consolidation_point_employee cpe on cpe.consolidation_point_id = cp.id " +
            "join employee e on e.id = cpe.employee_id " +
            "join account a on a.id = e.account_id " +
            "where a.id = :accountId " +
            "order by FIELD((select id from status where id = o.status_id), 6, 3, 4), o.id desc;")
    List<Orders> findByConsolidationPoints_Employee_IdAccount(@Param("accountId") long accountId);

    @Query(nativeQuery = true, value ="SELECT DISTINCT YEAR(end_Order) AS year " +
            "FROM Orders")
    List<Integer> findDistinctEndOrderYears();

    default List<DateEndOrder_YearDTO> dateEndOrder_Year() {
        List<Integer> years = findDistinctEndOrderYears();
        List<DateEndOrder_YearDTO> dtos = new ArrayList<>();
        long id = 0L;
        for (Integer year : years) {
            if (year != null) {
            dtos.add(new DateEndOrder_YearDTO(id++, year));}
        }
        return dtos;
    }

    @Query(nativeQuery = true, value ="SELECT DISTINCT YEAR(create_Order) AS year " +
            "FROM Orders")
    List<Integer> findDistinctCreateOrderYears();

    default List<DateEndOrder_YearDTO> dateCreateOrder_Year() {
        List<Integer> years = findDistinctCreateOrderYears();
        List<DateEndOrder_YearDTO> dtos = new ArrayList<>();
        long id = 0L;
        for (Integer year : years) {
            if (year != null) {
                dtos.add(new DateEndOrder_YearDTO(id++, year));}
        }
        return dtos;
    }

    @Query("SELECT cp.name, MONTH(o.createOrder) AS month, YEAR(o.createOrder) AS year, COUNT(o.id) AS received_orders " +
            "FROM ConsolidationPoint cp " +
            "INNER JOIN orders_consolidation_points ocp ON cp.id = ocp.consolidationPoint.id " +
            "INNER JOIN Orders o ON ocp.order.id = o.id " +
            "GROUP BY cp.name,  MONTH(o.createOrder), YEAR(o.createOrder) " +
            "ORDER BY YEAR(o.createOrder), MONTH(o.createOrder)")
    List<Object[]> getReceivedOrdersByConsolidationPoint();
    @Query("SELECT tp.name, MONTH(o.createOrder) AS month, YEAR(o.createOrder) AS year, COUNT(o.id) AS sent_orders " +
            "FROM TransactionPoint tp " +
            "LEFT JOIN Orders o ON tp.id = o.transactionPoint.id " +
            "GROUP BY tp.name, MONTH(o.createOrder), YEAR(o.createOrder) " +
            "ORDER BY YEAR(o.createOrder), MONTH(o.createOrder)")
    List<Object[]> getSentOrdersByTransactionPoint();

}
