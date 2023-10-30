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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionPointDTO {
    private long id;
    private String name;
    private String address;
    private LeaderDTO leader;
    private List<EmployeeDTO> employee;
    private ConsolidationPointDTO consolidationPoint;

    public TransactionPointDTO(long id, String name, String address, LeaderDTO leader, ConsolidationPointDTO consolidationPoint) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.leader = leader;
        this.consolidationPoint = consolidationPoint;
    }
}
