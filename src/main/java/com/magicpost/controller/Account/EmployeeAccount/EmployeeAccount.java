package com.magicpost.controller.Account.EmployeeAccount;

import com.magicpost.model.dto.CreateEmployeeRequest;
import com.magicpost.model.dto.EmployeeDTO;
import com.magicpost.service.IAccount;
import com.magicpost.service.IEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/employee")
public class EmployeeAccount {
    private final IEmployee iEmployee;
    private final IAccount iAccount;

    public EmployeeAccount(IEmployee iEmployee, IAccount iAccount) {
        this.iEmployee = iEmployee;
        this.iAccount = iAccount;
    }

    @PostMapping("createTran")
    public ResponseEntity<?> createEmployeeTransaction(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        Object o = iEmployee.createEmployeeTransaction(createEmployeeRequest);
        if (o.equals("account already exists")) {
            return ResponseEntity.ok("account already exists");
        }
        return ResponseEntity.ok((EmployeeDTO) o);
    }

    @PostMapping("createCon")
    public ResponseEntity<?> createEmployeeConsolidation(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        Object o = iEmployee.createEmployeeConsolidation(createEmployeeRequest);
        if (o.equals("account already exists")) {
            return ResponseEntity.ok("account already exists");
        }
        return ResponseEntity.ok((EmployeeDTO) o);
    }
    @GetMapping("{idAccount}/{idStatus}")
    public ResponseEntity<?> editStatusAccountEmployee(@PathVariable long idAccount , @PathVariable long idStatus){
        return ResponseEntity.ok(iAccount.editStatus(idAccount,idStatus));
    }
}
