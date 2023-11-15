package com.magicpost.service.serviceImpl;

import com.magicpost.model.dto.OrderStatistics_AdminDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;
import com.magicpost.service.IOrderStatistics_AdminDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatistics_AdminDTOImpl implements IOrderStatistics_AdminDTO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderStatistics_AdminDTO> getOrderStatisticsByYear_IdTransaction_IdStatus(long idTransaction, long idStatus, int year) {
        String query = "SELECT " +
                "    main.`Year`, " +
                "    main.`Month`, " +
                "    COALESCE(monthly_orders.TotalOrders_month, 0) AS TotalOrders_month " +
                "FROM " +
                "    ( " +
                "        SELECT DISTINCT " +
                "            YEAR(o.end_order) AS `Year`, " +
                "            MONTH(o.end_order) AS `Month`, " +
                "            o.transaction_point_id " +
                "        FROM " +
                "            orders o " +
                "        WHERE " +
                "            o.status_id = :idStatus " +
                "            AND YEAR(o.end_order) = :year " +
                "            AND  o.transaction_point_id = :idTransaction " +
                "        GROUP BY " +
                "            `Year`, `Month`, o.transaction_point_id " +
                "    ) AS main " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        YEAR(o.end_order) AS `Year`, " +
                "        MONTH(o.end_order) AS `Month`, " +
                "        o.transaction_point_id, " +
                "        COUNT(DISTINCT o.id) AS TotalOrders_month " +
                "    FROM " +
                "        orders o " +
                "    WHERE " +
                "        o.status_id = :idStatus " +
                "        AND YEAR(o.end_order) = :year " +
                "        AND o.transaction_point_id = :idTransaction " +
                "    GROUP BY " +
                "        `Year`, `Month`, o.transaction_point_id " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month` AND main.transaction_point_id = monthly_orders.transaction_point_id";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idStatus", idStatus)
                .setParameter("idTransaction", idTransaction);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_AdminDTO> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_AdminDTO orderStatistics = new OrderStatistics_AdminDTO(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }
        return orderStatisticsList;
    }

    @Override
    public List<OrderStatistics_AdminDTO> getOrderStatisticsByYear_IdConsolidation_IdStatus(long idConsolidation, long idStatus, int year) {
        String query = "SELECT " +
                "    main.`Year`, " +
                "    main.`Month`, " +
                "    COALESCE(monthly_orders.TotalOrders_month, 0) AS TotalOrders_month " +
                "FROM " +
                "    ( " +
                "        SELECT DISTINCT " +
                "            YEAR(o.end_order) AS `Year`, " +
                "            MONTH(o.end_order) AS `Month`, " +
                "            ocp.consolidation_points_id " +
                "        FROM " +
                "            orders o " +
                "        JOIN " +
                "            orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "        WHERE " +
                "            o.status_id = :idStatus " +
                "            AND YEAR(o.end_order) = :year " +
                "            AND ocp.consolidation_points_id = :idConsolidation " +
                "            AND ocp.id IN ( " +
                "                    SELECT MAX(ocp.id) AS max_ocp_id " +
                "                    FROM orders_consolidation_points ocp " +
                "                    GROUP BY ocp.orders_id " +
                "                    HAVING MAX(ocp.id) IS NOT NULL " +
                "        ) " +
                "        GROUP BY " +
                "            `Year`, `Month`, ocp.consolidation_points_id " +
                "    ) AS main " +
                "LEFT JOIN ( " +
                "    SELECT " +
                "        YEAR(o.end_order) AS `Year`, " +
                "        MONTH(o.end_order) AS `Month`, " +
                "        ocp.consolidation_points_id, " +
                "        COUNT(DISTINCT o.id) AS TotalOrders_month " +
                "    FROM " +
                "        orders o " +
                "    JOIN " +
                "        orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "    WHERE " +
                "        o.status_id = :idStatus " +
                "        AND YEAR(o.end_order) = :year " +
                "        AND ocp.consolidation_points_id = :idConsolidation " +
                "        AND ocp.id IN ( " +
                "                    SELECT MAX(ocp.id) AS max_ocp_id " +
                "                    FROM orders_consolidation_points ocp " +
                "                    GROUP BY ocp.orders_id " +
                "                    HAVING MAX(ocp.id) IS NOT NULL " +
                "        ) " +
                "    GROUP BY " +
                "        `Year`, `Month`, ocp.consolidation_points_id " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month` AND main.consolidation_points_id = monthly_orders.consolidation_points_id";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idStatus", idStatus)
                .setParameter("idConsolidation", idConsolidation);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_AdminDTO> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_AdminDTO orderStatistics = new OrderStatistics_AdminDTO(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }
        return orderStatisticsList;
    }
}
