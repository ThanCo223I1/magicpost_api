package com.magicpost.repo;

import com.magicpost.model.ConsolidationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IConsolidationPointRepo extends JpaRepository<ConsolidationPoint , Long> {
    ConsolidationPoint findByLeader_Id(long id);
    @Query(nativeQuery = true,value = "select consolidation_point_id from consolidation_point_employee c where c.employee_id=:idEmployee")
    long findIdConsolidationByIdEmployee(@Param("idEmployee")long idEmployee);
    List<ConsolidationPoint> findAllByStatus(int status);

    @Query(nativeQuery = true, value = "SELECT distinct cp.* " +
            "FROM consolidation_point cp " +
            "JOIN consolidation_point_employee cpe ON cpe.consolidation_point_id = cp.id " +
            "JOIN employee e on e.id = cpe.employee_id " +
            "JOIN account a ON e.account_id = a.id " +
            "WHERE a.id = :accountId")
    ConsolidationPoint findConsolidationPointsByAccountId(@Param("accountId") long accountId);

    @Query(nativeQuery = true, value = "SELECT DISTINCT cp.* " +
            "FROM consolidation_point cp " +
            "WHERE cp.id NOT IN (" +
            "SELECT DISTINCT cp.id " +
            "FROM consolidation_point cp " +
            "JOIN consolidation_point_employee cpe ON cpe.consolidation_point_id = cp.id " +
            "JOIN employee e on e.id = cpe.employee_id " +
            "JOIN account a ON e.account_id = a.id " +
            "WHERE a.id = :accountId" +
            ")")
    List<ConsolidationPoint> findAllByNotInAccountId(@Param("accountId") long accountId);

    @Query(nativeQuery = true, value = "select distinct cp.* " +
            "from consolidation_point cp " +
            "where cp.id = ( " +
            "select tp.consolidation_point_id " +
            "FROM transaction_point tp " +
            "JOIN transaction_point_employee tpe ON tpe.transaction_point_id = tp.id " +
            "JOIN employee e on e.id = tpe.employee_id " +
            "JOIN account a ON e.account_id = a.id " +
            "WHERE a.id = :accountId)")
    ConsolidationPoint findByTransactionPoint_AccountId(@Param("accountId") long accountId);

    @Query(nativeQuery = true, value = "SELECT DISTINCT cp.* " +
            "FROM Consolidation_Point cp " +
            "WHERE cp.id NOT IN ( " +
            "    SELECT DISTINCT cp.id " +
            "    FROM Consolidation_Point cp " +
            "    JOIN Consolidation_Point_employee cpe ON cpe.Consolidation_Point_id = cp.id " +
            "    JOIN employee e on e.id = cpe.employee_id " +
            "    JOIN Account a ON e.account_id = a.id " +
            "    WHERE a.id = :idAccount " +
            ") AND cp.id NOT IN ( " +
            "SELECT DISTINCT ocp.Consolidation_Points_id " +
            "    FROM Orders_Consolidation_Points ocp " +
            "    JOIN orders o ON o.id = ocp.orders_id " +
            "    JOIN Consolidation_Point cp ON cp.id = ocp.Consolidation_Points_id " +
            "    JOIN Consolidation_Point_employee cpe ON cpe.Consolidation_Point_id = cp.id " +
            "    JOIN employee e on e.id = cpe.employee_id " +
            "    JOIN Account a ON e.account_id = a.id " +
            "    WHERE o.id = :idOrder " +
            ")")
    List<ConsolidationPoint> findAllByNotInAccountId_AndNotConsolExistOrder(@Param("idAccount") long idAccount, @Param("idOrder") long idOrder);
}
