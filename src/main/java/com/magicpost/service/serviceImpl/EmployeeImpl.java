package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Employee;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateEmployeeRequest;
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
    private final CreateAccount create;


    public EmployeeImpl(IEmployeeRepo iEmployeeRepo, IAccount iAccount, ITransactionPoint iTransactionPoint, IConsolidationPoint iConsolidationPoint, CreateAccount create) {
        this.iEmployeeRepo = iEmployeeRepo;
        this.iAccount = iAccount;
        this.iTransactionPoint = iTransactionPoint;
        this.iConsolidationPoint = iConsolidationPoint;
        this.create = create;
    }

    @Override
    public Object createEmployeeTransaction(CreateEmployeeRequest createEmployeeRequest) {
        try {
            Account account = createEmployeeRequest.getAccount();
            Employee employee = createEmployeeRequest.getEmployee();
            employee.setAccount(create.create(account));
            employee = iEmployeeRepo.save(employee);
            TransactionPoint transactionPoint = iTransactionPoint.findById(createEmployeeRequest.getId()).get();
            transactionPoint.getEmployee().add(employee);
            iTransactionPoint.edit(transactionPoint);
            return employee.employeeDTO();
        } catch (Exception e) {
            return "account already exists";
        }

    }

    @Override
    public Object createEmployeeConsolidation(CreateEmployeeRequest createEmployeeRequest) {
        try {
            Account account = createEmployeeRequest.getAccount();
            Employee employee = createEmployeeRequest.getEmployee();
            employee.setAccount(create.create(account));
            employee = iEmployeeRepo.save(employee);
            ConsolidationPoint consolidationPoint = iConsolidationPoint.findById(createEmployeeRequest.getId());
            consolidationPoint.getEmployee().add(employee);
            iConsolidationPoint.save(consolidationPoint);
            return employee.employeeDTO();
        } catch (Exception e) {
            return "account already exists";
        }
    }

    @Override
    public Employee findByAccount_Id(long id) {
        return iEmployeeRepo.findAllByAccount_Id(id);
    }
}
