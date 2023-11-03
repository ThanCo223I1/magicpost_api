package com.magicpost.model;

import com.magicpost.model.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Status status;
    public AccountDTO accountDTO(){
        return new AccountDTO(this.id,this.username,this.role,this.status);
    }
}
