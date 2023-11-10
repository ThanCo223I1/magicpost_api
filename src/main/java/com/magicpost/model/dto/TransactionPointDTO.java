package com.magicpost.model.dto;

import com.magicpost.model.ConsolidationPoint;
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
public class TransactionPointDTO {
    private long id;
    private String name;
    private String address;
    private LeaderDTO leader;
    private List<EmployeeDTO> employee;
    private List<EmployeeDTO> employeeActive;
    private List<EmployeeDTO> employeeBlock;
    private ConsolidationPointDTO consolidationPoint;
    private int status;

    public TransactionPointDTO(long id, String name, String address, LeaderDTO leader, List<EmployeeDTO> employee, List<EmployeeDTO> employeeActive, List<EmployeeDTO> employeeBlock, ConsolidationPointDTO consolidationPoint, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.employee = employee;
        this.employeeActive = employeeActive;
        this.employeeBlock = employeeBlock;
        this.consolidationPoint = consolidationPoint;
        this.status = status;
    }

    public TransactionPointDTO(long id, String name, String address, LeaderDTO leader, List<EmployeeDTO> employee, ConsolidationPointDTO consolidationPoint, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.employee = employee;
        this.consolidationPoint = consolidationPoint;
        this.status = status;
    }

    public TransactionPointDTO(long id, String name, String address, LeaderDTO leader, List<EmployeeDTO> employee, ConsolidationPointDTO consolidationPoint) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.employee = employee;
        this.consolidationPoint = consolidationPoint;
    }

    public TransactionPointDTO(long id, String name, String address, LeaderDTO leader, ConsolidationPointDTO consolidationPoint) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.consolidationPoint = consolidationPoint;
    }
}
