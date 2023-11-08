package com.magicpost.repo;

import com.magicpost.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IEmployeeRepo extends JpaRepository<Employee , Long> {
    Employee findAllByAccount_Id(long id);

    @Query(nativeQuery = true,value = "select * from employee e join consolidation_point_employee c on c.employee_id=e.id where e.id=:idEmployee")
    Optional<Employee> findEmployeeConsolidation(@Param("idEmployee") long idEmployee);

    @Query(nativeQuery = true,value = "select * from employee e join transaction_point_employee t on t.employee_id=e.id where e.id=:idEmployee")
    Optional<Employee> findEmployeeTransaction(@Param("idEmployee") long idEmployee);


}
