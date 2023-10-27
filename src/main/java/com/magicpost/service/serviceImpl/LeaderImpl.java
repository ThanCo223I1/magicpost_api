package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import com.magicpost.repo.ILeaderRepo;
import com.magicpost.service.ILeader;
import org.springframework.stereotype.Service;

@Service
public class LeaderImpl implements ILeader {
    ILeaderRepo leaderRepo;

    public LeaderImpl(ILeaderRepo leaderRepo) {
        this.leaderRepo = leaderRepo;
    }

    @Override
    public Leader create(Account account, Leader leader) {
        leader.setAccount(account);
        return leaderRepo.save(leader);
    }
}
