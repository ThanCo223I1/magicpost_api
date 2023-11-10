package com.magicpost.model;

import com.magicpost.model.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private String avatar;
    private String idCard;
    @ManyToOne
    private Role role;
    @OneToOne
    private Account account;
    public EmployeeDTO employeeDTO (){
        return new EmployeeDTO(this.id,this.account.getId(),this.account.getStatus().getId(),this.name
                ,this.phoneNumber,this.address,this.email,this.avatar,this.idCard,this.role);
    }
}
