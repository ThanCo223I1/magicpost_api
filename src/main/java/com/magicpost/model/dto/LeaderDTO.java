package com.magicpost.model.dto;

import com.magicpost.model.Account;
import com.magicpost.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaderDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private Role role;
}
