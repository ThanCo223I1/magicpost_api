package com.magicpost.repo;

import com.magicpost.model.TransactionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransactionPointRepo extends JpaRepository<TransactionPoint , Long> {
    TransactionPoint findByLeader_Id(long id);
    @Query(nativeQuery = true,value = "select transaction_point_id from transaction_point_employee t where t.employee_id=:idEmployee")
    long findIdTransactionByIdEmployee(@Param("idEmployee")long idEmployee);
    List<TransactionPoint> findAllByStatus(int status);

}
