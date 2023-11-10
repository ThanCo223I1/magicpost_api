package com.magicpost.controller.Account.leaderAccount;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import com.magicpost.model.dto.AccountDTO;
import com.magicpost.service.ILeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/leader")
public class LeaderAccount {
    private final ILeader ileader;

    public LeaderAccount(ILeader leader) {
        this.ileader = leader;
    }

    @PostMapping("create")
    public ResponseEntity<Leader> create(Leader leader, Account account) {
        return ResponseEntity.ok(ileader.create(account, leader));
    }

    @GetMapping("/account/{idAccount}")
    public Leader findByIdAccount(@PathVariable long idAccount) {
        return ileader.findByAccount_Id(idAccount);
    }
}
