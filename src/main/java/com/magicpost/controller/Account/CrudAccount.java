package com.magicpost.controller.Account;

import com.magicpost.model.Account;
import com.magicpost.model.dto.AccountDTO;
import com.magicpost.service.IAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class CrudAccount {
    IAccount account;

    public CrudAccount(IAccount account) {
        this.account = account;
    }
    @GetMapping
    public ResponseEntity<List<Account>> getAll(){
        return ResponseEntity.ok(account.getAll());
    }
    @PostMapping("create")
    public ResponseEntity<Account> create(Account newAccount){
        return ResponseEntity.ok(account.create(newAccount));
    }
}
