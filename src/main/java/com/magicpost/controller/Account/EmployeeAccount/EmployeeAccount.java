package com.magicpost.controller.Account.EmployeeAccount;

import com.magicpost.model.dto.CreateEmployeeRequest;
import com.magicpost.model.dto.EmployeeDTO;
import com.magicpost.service.IEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/employee")
public class EmployeeAccount {
    private final IEmployee iEmployee;

    public EmployeeAccount(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
    }

    @PostMapping("createTran")
    public ResponseEntity<EmployeeDTO> createEmployeeTransaction(CreateEmployeeRequest createEmployeeRequest) {
        return ResponseEntity.ok(iEmployee.createEmployeeTransaction(createEmployeeRequest));
    }
    @PostMapping("createCon")
    public ResponseEntity<EmployeeDTO> createEmployeeConsolidation(CreateEmployeeRequest createEmployeeRequest) {
        return ResponseEntity.ok(iEmployee.createEmployeeConsolidation(createEmployeeRequest));
    }

}
