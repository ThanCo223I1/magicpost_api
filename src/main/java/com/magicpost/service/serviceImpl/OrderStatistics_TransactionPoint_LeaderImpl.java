package com.magicpost.service.serviceImpl;

import com.magicpost.model.dto.OrderStatistics_TransactionPoint_Leader;
import com.magicpost.service.IOrderStatistics_TransactionPoint_LeaderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatistics_TransactionPoint_LeaderImpl implements IOrderStatistics_TransactionPoint_LeaderService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsEndOrderByYear_IdStatus(long idAccount, long idStatus, int year) {
        String query = "SELECT " +
                "    main.`Year`, " +
                "    main.`Month`, " +
                "    COALESCE(monthly_orders.TotalOrders_month, 0) AS TotalOrders_month " +
                "FROM " +
                "    ( " +
                "        SELECT DISTINCT " +
                "            YEAR(o.end_order) AS `Year`, " +
                "            MONTH(o.end_order) AS `Month` " +
                "        FROM " +
                "            orders o " +
                "        JOIN " +
                "            transaction_point tp ON tp.id = o.transaction_point_id " +
                "        JOIN " +
                "            leader l ON l.id = tp.leader_id " +
                "        WHERE " +
                "            YEAR(o.end_order) = :year " +
                "            AND o.status_id = :idStatus " +
                "        GROUP BY " +
                "            `Year`, `Month` " +
                "    ) AS main " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        YEAR(o.end_order) AS `Year`, " +
                "        MONTH(o.end_order) AS `Month`, " +
                "        COUNT(DISTINCT o.id) AS TotalOrders_month " +
                "    FROM " +
                "        orders o " +
                "    JOIN " +
                "        transaction_point tp ON tp.id = o.transaction_point_id " +
                "    JOIN " +
                "        leader l ON l.id = tp.leader_id " +
                "    WHERE " +
                "        YEAR(o.end_order) = :year " +
                "        AND o.status_id = :idStatus " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month`";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idStatus", idStatus)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_TransactionPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_TransactionPoint_Leader orderStatistics = new OrderStatistics_TransactionPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }
        return orderStatisticsList;
    }

    @Override
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_IdStatus_Unsent(long idAccount, int year) {
        String query = "SELECT " +
                "    main.`Year`, " +
                "    main.`Month`, " +
                "    COALESCE(monthly_orders.TotalOrders_month, 0) AS TotalOrders_month " +
                "FROM " +
                "    ( " +
                "        SELECT DISTINCT " +
                "            YEAR(o.create_order) AS `Year`, " +
                "            MONTH(o.create_order) AS `Month` " +
                "        FROM " +
                "            orders o " +
                "        JOIN " +
                "            transaction_point tp ON tp.id = o.transaction_point_id " +
                "        JOIN " +
                "            leader l ON l.id = tp.leader_id " +
                "        WHERE " +
                "            YEAR(o.create_order) = :year " +
                "            AND o.id IN ( " +
                "    SELECT id " +
                "    FROM orders " +
                "    WHERE id NOT IN ( " +
                "    SELECT DISTINCT orders_id  " +
                "                    FROM orders_consolidation_points " +
                "    ) " +
                "            ) " +
                "            AND l.account_id = :idAccount " +
                "        GROUP BY " +
                "            `Year`, `Month` " +
                "    ) AS main " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        YEAR(o.create_order) AS `Year`, " +
                "        MONTH(o.create_order) AS `Month`, " +
                "        COUNT(DISTINCT o.id) AS TotalOrders_month " +
                "    FROM " +
                "        orders o " +
                "    JOIN " +
                "        transaction_point tp ON tp.id = o.transaction_point_id " +
                "    JOIN " +
                "        leader l ON l.id = tp.leader_id " +
                "    WHERE " +
                "        YEAR(o.create_order) = :year " +
                "        AND o.id IN ( " +
                "                   SELECT id " +
                "                   FROM orders " +
                "                   WHERE id NOT IN ( " +
                "                                  SELECT DISTINCT orders_id  " +
                "                                  FROM orders_consolidation_points " +
                "                         ) " +
                "            ) " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month`";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_TransactionPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_TransactionPoint_Leader orderStatistics = new OrderStatistics_TransactionPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }
        return orderStatisticsList;
    }

    @Override
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_IdStatus_Received(long idAccount, int year) {
        String query = "SELECT " +
                "    main.`Year`, " +
                "    main.`Month`, " +
                "    COALESCE(monthly_orders.TotalOrders_month, 0) AS TotalOrders_month " +
                "FROM " +
                "    ( " +
                "        SELECT DISTINCT " +
                "            YEAR(o.create_order) AS `Year`, " +
                "            MONTH(o.create_order) AS `Month` " +
                "        FROM " +
                "            orders o " +
                "        JOIN " +
                "            transaction_point tp ON tp.id = o.transaction_point_id " +
                "        JOIN " +
                "            leader l ON l.id = tp.leader_id " +
                "        WHERE " +
                "            YEAR(o.create_order) = :year " +
                "            AND o.status_id = 6 " +
                "        GROUP BY " +
                "            `Year`, `Month` " +
                "    ) AS main " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        YEAR(o.create_order) AS `Year`, " +
                "        MONTH(o.create_order) AS `Month`, " +
                "        COUNT(DISTINCT o.id) AS TotalOrders_month " +
                "    FROM " +
                "        orders o " +
                "    JOIN " +
                "        transaction_point tp ON tp.id = o.transaction_point_id " +
                "    JOIN " +
                "        leader l ON l.id = tp.leader_id " +
                "    WHERE " +
                "        YEAR(o.create_order) = :year " +
                "        AND o.status_id = 6 " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month`";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_TransactionPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_TransactionPoint_Leader orderStatistics = new OrderStatistics_TransactionPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }
        return orderStatisticsList;
    }

    @Override
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_Sent(long idAccount, int year) {
        String query = "SELECT " +
                "    main.`Year`, " +
                "    main.`Month`, " +
                "    COALESCE(monthly_orders.TotalOrders_month, 0) AS TotalOrders_month " +
                "FROM " +
                "    ( " +
                "        SELECT DISTINCT " +
                "            YEAR(o.create_order) AS `Year`, " +
                "            MONTH(o.create_order) AS `Month` " +
                "        FROM " +
                "            orders o " +
                "        JOIN " +
                "            orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "        JOIN " +
                "            transaction_point tp ON tp.id = o.transaction_point_id " +
                "        JOIN " +
                "            leader l ON l.id = tp.leader_id " +
                "        WHERE " +
                "            YEAR(o.create_order) = :year " +
                "            AND ocp.orders_id IS NOT NULL " +
                "        GROUP BY " +
                "            `Year`, `Month` " +
                "    ) AS main " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        YEAR(o.create_order) AS `Year`, " +
                "        MONTH(o.create_order) AS `Month`, " +
                "        COUNT(DISTINCT o.id) AS TotalOrders_month " +
                "    FROM " +
                "        orders o " +
                "    JOIN " +
                "        orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "    JOIN " +
                "        transaction_point tp ON tp.id = o.transaction_point_id " +
                "    JOIN " +
                "        leader l ON l.id = tp.leader_id " +
                "    WHERE " +
                "        YEAR(o.create_order) = :year " +
                "        AND ocp.orders_id IS NOT NULL " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month`";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_TransactionPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_TransactionPoint_Leader orderStatistics = new OrderStatistics_TransactionPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }
        return orderStatisticsList;
    }
}
