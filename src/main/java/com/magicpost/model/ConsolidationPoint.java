package com.magicpost.model;

import com.magicpost.model.dto.ConsolidationPointDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne
    private Employee employee;
    public ConsolidationPointDTO consolidationPointDTO(){
        return new ConsolidationPointDTO(this.id,this.name,this.address);
    }
}
