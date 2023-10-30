package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Leader;
import com.magicpost.model.dto.ConsolidationPointDTO;
import com.magicpost.model.dto.CreateConsolidationRequest;
import com.magicpost.repo.IConsolidationPointRepo;
import com.magicpost.service.IAccount;
import com.magicpost.service.IConsolidationPoint;
import com.magicpost.service.ILeader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ConsolidationImpl implements IConsolidationPoint {
    private final IConsolidationPointRepo iConsolidationPointRepo;
    private final IAccount iAccount;
    private final ILeader iLeader;

    public ConsolidationImpl(IConsolidationPointRepo iConsolidationPointRepo, IAccount iAccount, ILeader iLeader) {
        this.iConsolidationPointRepo = iConsolidationPointRepo;
        this.iAccount = iAccount;
        this.iLeader = iLeader;
    }

    @Override
    public List<ConsolidationPointDTO> getAll() {
        List<ConsolidationPoint> consolidationPoints = iConsolidationPointRepo.findAll();
        List<ConsolidationPointDTO> consolidationPointDTOS = new ArrayList<>();
        for (ConsolidationPoint c: consolidationPoints) {
            consolidationPointDTOS.add(c.consolidationPointDTO());
        }
        return consolidationPointDTOS;
    }

    @Override
    public ConsolidationPointDTO create(CreateConsolidationRequest createConsolidationRequest) {
        Account account = createConsolidationRequest.getAccount();
        Leader leader = createConsolidationRequest.getLeader();
        ConsolidationPoint consolidationPoint = createConsolidationRequest.getConsolidationPoint();
        iAccount.create(account);
        iLeader.create(account,leader);
        consolidationPoint.setLeader(leader);
        iConsolidationPointRepo.save(consolidationPoint);
        return consolidationPoint.consolidationPointDTOLeader();
    }
}
