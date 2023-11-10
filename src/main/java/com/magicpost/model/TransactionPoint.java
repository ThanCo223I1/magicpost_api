package com.magicpost.model;

import com.magicpost.model.dto.EmployeeDTO;
import com.magicpost.model.dto.TransactionPointDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    @OneToOne
    private Leader leader;
    @OneToMany
    private List<Employee> employee;
    @ManyToOne
    private ConsolidationPoint consolidationPoint;
    private int status;

    public TransactionPointDTO transactionPointDTO() {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        if (employee != null) {
            for (Employee e : this.employee) {
                employeeDTOS.add(e.employeeDTO());
            }
        }
        return new TransactionPointDTO(this.id, this.name, this.address, this.leader.leaderDTO()
                , employeeDTOS, this.consolidationPoint.noEmployeeConsolidationPointDTO(), this.status);
    }

    public TransactionPointDTO noEmployeeTransactionPointDTO() {
        return new TransactionPointDTO(this.id, this.name, this.address, this.leader.leaderDTO()
                , this.consolidationPoint.noEmployeeConsolidationPointDTO());
    }
}
