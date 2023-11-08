package com.magicpost.model.dto;

import com.magicpost.model.Employee;
import com.magicpost.model.Leader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ConsolidationPointDTO {
    private long id;
    private String name;
    private String address;
    private LeaderDTO leader;
    private List<EmployeeDTO> employee;
    private List<EmployeeDTO> employeeActive;
    private List<EmployeeDTO> employeeBlock;

    public ConsolidationPointDTO(long id, String name, String address, LeaderDTO leader, List<EmployeeDTO> employee, List<EmployeeDTO> employeeActive, List<EmployeeDTO> employeeBlock, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.employee = employee;
        this.employeeActive = employeeActive;
        this.employeeBlock = employeeBlock;
        this.status = status;
    }

    private int status;

    public ConsolidationPointDTO(long id, String name, String address, LeaderDTO leader, List<EmployeeDTO> employee, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.employee = employee;
        this.status = status;
    }

    public ConsolidationPointDTO(long id, String name, String address, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status=status;
    }

    public ConsolidationPointDTO(long id, String name, String address, LeaderDTO leader) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
    }
}

