package com.magicpost.repo;

import com.magicpost.model.TransactionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITransactionPointRepo extends JpaRepository<TransactionPoint , Long> {
    @Query(nativeQuery = true, value = "SELECT distinct tp.* " +
            "FROM transaction_point tp " +
            "JOIN transaction_point_employee tpe ON tpe.transaction_point_id = tp.id " +
            "JOIN employee e on e.id = tpe.employee_id " +
            "JOIN account a ON e.account_id = a.id " +
            "WHERE a.id = :accountId")
    TransactionPoint findTransactionPointsByAccountId(@Param("accountId") long accountId);
}
