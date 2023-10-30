package com.magicpost.model.dto;

import com.magicpost.model.Account;
import com.magicpost.model.Status;
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
public class EmployeeDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private String avatar;
    private String idCard;
    private Status status;
}
