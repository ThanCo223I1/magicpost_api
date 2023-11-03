package com.magicpost.service;

import com.magicpost.model.Account;
import com.magicpost.model.Employee;
import com.magicpost.model.dto.CreateEmployeeRequest;
import com.magicpost.model.dto.EmployeeDTO;

import java.util.List;

public interface IEmployee {
    Object createEmployeeTransaction(CreateEmployeeRequest createEmployeeRequest);
    Object createEmployeeConsolidation(CreateEmployeeRequest createEmployeeRequest);
    Employee  findByAccount_Id(long id);
}
