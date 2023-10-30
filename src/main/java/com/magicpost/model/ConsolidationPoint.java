package com.magicpost.model;

import com.magicpost.model.dto.ConsolidationPointDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    @OneToOne
    private Leader leader;
    @OneToMany
    private List<Employee> employee;
    public ConsolidationPointDTO consolidationPointDTO(){
        return new ConsolidationPointDTO(this.id,this.name,this.address);
    }
    public ConsolidationPointDTO consolidationPointDTOLeader(){
        return new ConsolidationPointDTO(this.id,this.name,this.address,this.leader.leaderDTO());
    }
}
