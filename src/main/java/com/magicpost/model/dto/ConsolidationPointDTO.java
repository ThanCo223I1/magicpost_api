package com.magicpost.model.dto;

import com.magicpost.model.Employee;
import com.magicpost.model.Leader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsolidationPointDTO {
    private long id;
    private String name;
    private String address;
}
