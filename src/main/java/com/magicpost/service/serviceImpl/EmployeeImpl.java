package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Employee;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateEmployeeRequest;
import com.magicpost.model.dto.EmployeeDTO;
import com.magicpost.repo.IEmployeeRepo;
import com.magicpost.service.IAccount;
import com.magicpost.service.IConsolidationPoint;
import com.magicpost.service.IEmployee;
import com.magicpost.service.ITransactionPoint;
import org.springframework.stereotype.Service;

@Service
public class EmployeeImpl implements IEmployee {
    private final IEmployeeRepo iEmployeeRepo;
    private final IAccount iAccount;
    private final ITransactionPoint iTransactionPoint;
    private final IConsolidationPoint iConsolidationPoint;

    public EmployeeImpl(IEmployeeRepo iEmployeeRepo, IAccount iAccount, ITransactionPoint iTransactionPoint, IConsolidationPoint iConsolidationPoint) {
        this.iEmployeeRepo = iEmployeeRepo;
        this.iAccount = iAccount;
        this.iTransactionPoint = iTransactionPoint;
        this.iConsolidationPoint = iConsolidationPoint;
    }

    @Override
    public EmployeeDTO createEmployeeTransaction(CreateEmployeeRequest createEmployeeRequest) {
        Account account = createEmployeeRequest.getAccount();
        Employee employee = createEmployeeRequest.getEmployee();
        employee.setAccount(iAccount.create(account));
        TransactionPoint transactionPoint = iTransactionPoint.findById(createEmployeeRequest.getId()).get();
        transactionPoint.getEmployee().add(employee);
        iTransactionPoint.edit(transactionPoint);
        return iEmployeeRepo.save(employee).employeeDTO();
    }

    @Override
    public EmployeeDTO createEmployeeConsolidation(CreateEmployeeRequest createEmployeeRequest) {
        Account account = createEmployeeRequest.getAccount();
        Employee employee = createEmployeeRequest.getEmployee();
        employee.setAccount(iAccount.create(account));
        ConsolidationPoint consolidationPoint = iConsolidationPoint.findById(createEmployeeRequest.getId());
        consolidationPoint.getEmployee().add(employee);
        iConsolidationPoint.save(consolidationPoint);
        return iEmployeeRepo.save(employee).employeeDTO();
    }
}
