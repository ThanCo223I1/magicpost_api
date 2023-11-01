package com.magicpost.controller.Account.EmployeeAccount;

import com.magicpost.model.dto.CreateEmployeeRequest;
import com.magicpost.model.dto.EmployeeDTO;
import com.magicpost.service.IEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/employee")
public class EmployeeAccount {
    private final IEmployee iEmployee;

    public EmployeeAccount(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
    }

    @PostMapping("createTran")
    public ResponseEntity<EmployeeDTO> createEmployeeTransaction(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        return ResponseEntity.ok(iEmployee.createEmployeeTransaction(createEmployeeRequest));
    }
    @PostMapping("createCon")
    public ResponseEntity<EmployeeDTO> createEmployeeConsolidation(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        return ResponseEntity.ok(iEmployee.createEmployeeConsolidation(createEmployeeRequest));
    }

}
