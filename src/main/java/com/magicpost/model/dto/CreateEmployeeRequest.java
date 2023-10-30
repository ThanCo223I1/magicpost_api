package com.magicpost.model.dto;

import com.magicpost.model.Account;
import com.magicpost.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeRequest {
    private Account account;
    private Employee employee;
    private long id;
}
