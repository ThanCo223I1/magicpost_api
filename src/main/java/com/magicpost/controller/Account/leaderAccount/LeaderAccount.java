package com.magicpost.controller.Account.leaderAccount;

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
    ILeader leader;

    public LeaderAccount(ILeader leader) {
        this.leader = leader;
    }
    @PostMapping("create")
    public ResponseEntity<Leader> create(Leader leader , AccountDTO accountDTO){
        return null;
    }
}
