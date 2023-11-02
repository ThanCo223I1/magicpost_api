package com.magicpost.model.dto;

import com.magicpost.model.Role;
import com.magicpost.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private long id;
    private String username;
    private Role role;
    private Status status;
    private String token;
    private LeaderDTO leaderDTO;
    private EmployeeDTO employeeDTO;

    public AccountDTO(long id, String username, Role role, Status status, String token, EmployeeDTO employeeDTO) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.status = status;
        this.token = token;
        this.employeeDTO = employeeDTO;
    }

    public AccountDTO(long id, String username, Role role, Status status, String token, LeaderDTO leaderDTO) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.status = status;
        this.token = token;
        this.leaderDTO = leaderDTO;
    }

    public AccountDTO(long id, String username, Status status, Role role, String token) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.role = role;
        this.token = token;
    }

    public AccountDTO(long id, String username, Role role, Status status) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.status = status;
    }
}
