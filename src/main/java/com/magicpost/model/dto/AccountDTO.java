package com.magicpost.model.dto;

import com.magicpost.model.Role;
import com.magicpost.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private long id;
    private String username;
    private Role role;
    private Status status;
}
