package com.magicpost.repo;

import com.magicpost.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee , Long> {
    Employee findAllByAccount_Id(long id);
}
