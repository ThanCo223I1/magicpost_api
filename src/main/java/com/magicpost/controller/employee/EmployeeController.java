package com.magicpost.controller.employee;

import com.magicpost.model.Employee;
import com.magicpost.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    IEmployee iEmployee;

    @GetMapping("/account/{accountId}")
    public Employee findByAccountId(@PathVariable long accountId) {
        return iEmployee.findByAccount_Id(accountId);
    }
}
