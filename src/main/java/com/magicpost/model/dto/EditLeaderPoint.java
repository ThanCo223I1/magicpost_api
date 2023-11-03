package com.magicpost.model.dto;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditLeaderPoint {
    private long id;
    private long idAccount;
    private String password;
    private Account account;
    private Leader leader;
}
