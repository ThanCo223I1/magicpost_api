package com.magicpost.controller.Account.leaderAccount;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import com.magicpost.model.dto.AccountDTO;
import com.magicpost.service.ILeader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/leader")
public class LeaderAccount {
    ILeader ileader;

    public LeaderAccount(ILeader leader) {
        this.ileader = leader;
    }
    @PostMapping("create")
    public ResponseEntity<Leader> create(Leader leader , Account account){
        return ResponseEntity.ok(ileader.create(account,leader));
    }
}
