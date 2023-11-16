package com.magicpost.service.serviceImpl;

import com.magicpost.model.dto.DateEndOrder_YearDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;
import com.magicpost.repo.IOrdersRepo;
import com.magicpost.service.IOrderStatistics_ConsolidationPoint_LeaderService;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatistics_ConsolidationPoint_LeaderImpl implements IOrderStatistics_ConsolidationPoint_LeaderService {
    @Autowired
    IOrdersRepo iOrdersRepo;

    @Override
    public List<DateEndOrder_YearDTO> dateEndOrder_Year() {
        return iOrdersRepo.dateEndOrder_Year();
    }

    @Override
    public List<DateEndOrder_YearDTO> dateCreateOrder_Year() {
        return iOrdersRepo.dateCreateOrder_Year();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusComplete(int year, long idAccount) {
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
                "            orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "        JOIN " +
                "            consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "        JOIN " +
                "            leader l ON l.id = cp.leader_id " +
                "        WHERE " +
                "            ocp.id IN ( " +
                "                SELECT ocp.id " +
                "                FROM orders_consolidation_points ocp " +
                "                JOIN consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "                JOIN leader l ON l.id = cp.leader_id " +
                "                WHERE l.account_id = :idAccount " +
                "                    AND ocp.id IN ( " +
                "                        SELECT MAX(ocp.id) AS max_ocp_id " +
                "                        FROM orders_consolidation_points ocp " +
                "                        GROUP BY ocp.orders_id " +
                "                        HAVING MAX(ocp.id) IS NOT NULL " +
                "                    ) " +
                "            ) " +
                "            AND YEAR(o.end_order) = :year " +
                "            AND o.status_id = 3 " +
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
                "        orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "    JOIN " +
                "        consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "    JOIN " +
                "        leader l ON l.id = cp.leader_id " +
                "    WHERE " +
                "        ocp.id IN ( " +
                "            SELECT ocp.id " +
                "            FROM orders_consolidation_points ocp " +
                "            JOIN consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "            JOIN leader l ON l.id = cp.leader_id " +
                "            WHERE l.account_id = :idAccount " +
                "                AND ocp.id IN ( " +
                "                    SELECT MAX(ocp.id) AS max_ocp_id " +
                "                    FROM orders_consolidation_points ocp " +
                "                    GROUP BY ocp.orders_id " +
                "                    HAVING MAX(ocp.id) IS NOT NULL " +
                "                ) " +
                "        ) " +
                "        AND YEAR(o.end_order) = :year " +
                "        AND o.status_id = 3 " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month` " +
                " order by main.`Month` asc";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_ConsolidationPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_ConsolidationPoint_Leader orderStatistics = new OrderStatistics_ConsolidationPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }

        return orderStatisticsList;
    }


    @Override
    public List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusCancel(int year, long idAccount) {
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
                "            orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "        JOIN " +
                "            consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "        JOIN " +
                "            leader l ON l.id = cp.leader_id " +
                "        WHERE " +
                "            ocp.id IN ( " +
                "                SELECT ocp.id " +
                "                FROM orders_consolidation_points ocp " +
                "                JOIN consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "                JOIN leader l ON l.id = cp.leader_id " +
                "                WHERE l.account_id = :idAccount " +
                "                    AND ocp.id IN ( " +
                "                        SELECT MAX(ocp.id) AS max_ocp_id " +
                "                        FROM orders_consolidation_points ocp " +
                "                        GROUP BY ocp.orders_id " +
                "                        HAVING MAX(ocp.id) IS NOT NULL " +
                "                    ) " +
                "            ) " +
                "            AND YEAR(o.end_order) = :year " +
                "            AND o.status_id = 4 " +
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
                "        orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "    JOIN " +
                "        consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "    JOIN " +
                "        leader l ON l.id = cp.leader_id " +
                "    WHERE " +
                "        ocp.id IN ( " +
                "            SELECT ocp.id " +
                "            FROM orders_consolidation_points ocp " +
                "            JOIN consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "            JOIN leader l ON l.id = cp.leader_id " +
                "            WHERE l.account_id = :idAccount " +
                "                AND ocp.id IN ( " +
                "                    SELECT MAX(ocp.id) AS max_ocp_id " +
                "                    FROM orders_consolidation_points ocp " +
                "                    GROUP BY ocp.orders_id " +
                "                    HAVING MAX(ocp.id) IS NOT NULL " +
                "                ) " +
                "        ) " +
                "        AND YEAR(o.end_order) = :year " +
                "        AND o.status_id = 4 " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month` " +
                " order by main.`Month` asc";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_ConsolidationPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_ConsolidationPoint_Leader orderStatistics = new OrderStatistics_ConsolidationPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }

        return orderStatisticsList;
    }

    @Override
    public List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusShipping(int year, long idAccount) {
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
                "            consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "        JOIN " +
                "            leader l ON l.id = cp.leader_id " +
                "        WHERE " +
                "            ocp.id IN ( " +
                "                SELECT ocp.id " +
                "                FROM orders_consolidation_points ocp " +
                "                JOIN consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "                JOIN leader l ON l.id = cp.leader_id " +
                "                WHERE l.account_id = :idAccount " +
                "                    AND ocp.id IN ( " +
                "                        SELECT MAX(ocp.id) AS max_ocp_id " +
                "                        FROM orders_consolidation_points ocp " +
                "                        GROUP BY ocp.orders_id " +
                "                        HAVING MAX(ocp.id) IS NOT NULL " +
                "                    ) " +
                "            ) " +
                "            AND YEAR(o.create_order) = :year " +
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
                "        orders_consolidation_points ocp ON ocp.orders_id = o.id " +
                "    JOIN " +
                "        consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "    JOIN " +
                "        leader l ON l.id = cp.leader_id " +
                "    WHERE " +
                "        ocp.id IN ( " +
                "            SELECT ocp.id " +
                "            FROM orders_consolidation_points ocp " +
                "            JOIN consolidation_point cp ON cp.id = ocp.consolidation_points_id " +
                "            JOIN leader l ON l.id = cp.leader_id " +
                "            WHERE l.account_id = :idAccount " +
                "                AND ocp.id IN ( " +
                "                    SELECT MAX(ocp.id) AS max_ocp_id " +
                "                    FROM orders_consolidation_points ocp " +
                "                    GROUP BY ocp.orders_id " +
                "                    HAVING MAX(ocp.id) IS NOT NULL " +
                "                ) " +
                "        ) " +
                "        AND YEAR(o.create_order) = :year " +
                "        AND o.status_id = 6 " +
                "        AND l.account_id = :idAccount " +
                "    GROUP BY " +
                "        `Year`, `Month` " +
                ") AS monthly_orders ON main.`Year` = monthly_orders.`Year` AND main.`Month` = monthly_orders.`Month` " +
                " order by main.`Month` asc";

        Query nativeQuery = entityManager.createNativeQuery(query)
                .setParameter("year", year)
                .setParameter("idAccount", idAccount);

        List<Object[]> results = nativeQuery.getResultList();
        List<OrderStatistics_ConsolidationPoint_Leader> orderStatisticsList = new ArrayList<>();

        for (Object[] result : results) {
            int resultYear = (int) result[0];
            int resultMonth = (int) result[1];
            BigInteger totalOrdersMonth = (BigInteger) result[2];

            OrderStatistics_ConsolidationPoint_Leader orderStatistics = new OrderStatistics_ConsolidationPoint_Leader(resultYear, resultMonth, totalOrdersMonth);
            orderStatisticsList.add(orderStatistics);
        }

        return orderStatisticsList;
    }
}
