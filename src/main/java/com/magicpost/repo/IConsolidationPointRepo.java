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
}
