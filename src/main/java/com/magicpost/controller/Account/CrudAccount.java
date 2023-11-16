package com.magicpost.controller.Account;

import com.magicpost.model.Account;
import com.magicpost.service.IAccount;
import com.magicpost.service.serviceImpl.CreateAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class CrudAccount {
    IAccount account;
   private final CreateAccount create;

    public CrudAccount(IAccount account, CreateAccount create) {
        this.account = account;
        this.create = create;
    }
    @GetMapping
    public ResponseEntity<List<Account>> getAll(){
        return ResponseEntity.ok(account.getAll());
    }
    @PostMapping("create")
    public ResponseEntity<Account> create(Account newAccount){
        return ResponseEntity.ok(create.create(newAccount));
    }
}
