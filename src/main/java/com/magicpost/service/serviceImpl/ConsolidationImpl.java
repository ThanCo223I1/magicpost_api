package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Leader;
import com.magicpost.model.dto.*;
import com.magicpost.repo.IConsolidationPointRepo;
import com.magicpost.service.IAccount;
import com.magicpost.service.IConsolidationPoint;
import com.magicpost.service.ILeader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsolidationImpl implements IConsolidationPoint {
    private final IConsolidationPointRepo iConsolidationPointRepo;
    private final IAccount iAccount;
    private final ILeader iLeader;
    private final CreateAccount create;


    public ConsolidationImpl(IConsolidationPointRepo iConsolidationPointRepo, IAccount iAccount, ILeader iLeader, CreateAccount create) {
        this.iConsolidationPointRepo = iConsolidationPointRepo;
        this.iAccount = iAccount;
        this.iLeader = iLeader;
        this.create = create;
    }

    @Override
    public List<ConsolidationPointDTO> findAllByStatus(int status) {
        List<ConsolidationPoint> consolidationPoints = iConsolidationPointRepo.findAllByStatus(status);
        List<ConsolidationPointDTO> consolidationPointDTOS = new ArrayList<>();
        for (ConsolidationPoint c : consolidationPoints) {
            consolidationPointDTOS.add(c.consolidationPointDTOLeader());
        }
        return consolidationPointDTOS;
    }

    @Override
    public ConsolidationPointDTO create(CreateConsolidationRequest createConsolidationRequest) {
        Account account = createConsolidationRequest.getAccount();
        Leader leader = createConsolidationRequest.getLeader();
        ConsolidationPoint consolidationPoint = createConsolidationRequest.getConsolidationPoint();
        create.create(account);
        iLeader.create(account, leader);
        consolidationPoint.setLeader(leader);
        iConsolidationPointRepo.save(consolidationPoint);
        return consolidationPoint.noEmployeeConsolidationPointDTO();
    }

    @Override
    public ConsolidationPoint findById(long id) {
        return iConsolidationPointRepo.findById(id).get();
    }

    @Override
    public ConsolidationPoint save(ConsolidationPoint consolidationPoint) {
        return iConsolidationPointRepo.save(consolidationPoint);
    }

    @Override
    public ConsolidationPointDTO findByLeader_Id(long id) {
        return iConsolidationPointRepo.findByLeader_Id(id).consolidationPointDTOLeader();
    }

    @Override
    public long findIdConsolidationByEmployee(long idEmployee) {
        return iConsolidationPointRepo.findIdConsolidationByIdEmployee(idEmployee);
    }

    @Override
    public ConsolidationPointDTO saveStatus(long id, int status) {
        ConsolidationPoint consolidationPoint = iConsolidationPointRepo.findById(id).get();
        consolidationPoint.setStatus(status);
        return iConsolidationPointRepo.save(consolidationPoint).noEmployeeConsolidationPointDTO();
    }

    @Override
    public Object saveLeader(EditLeaderPoint editLeaderPoint) {
        Account account = iAccount.findById(editLeaderPoint.getIdAccount()).get();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        if (account.getPassword().equals(editLeaderPoint.getPassword())) {
        if (passwordEncoder.matches(editLeaderPoint.getPassword(), account.getPassword())) {
            Account newAccount;
            try {
                newAccount = create.create(editLeaderPoint.getAccount());
                Leader leader = editLeaderPoint.getLeader();
                leader = iLeader.create(newAccount, leader);
                ConsolidationPoint consolidationPoint = iConsolidationPointRepo.findById(editLeaderPoint.getId()).get();
                consolidationPoint.setLeader(leader);
                return iConsolidationPointRepo.save(consolidationPoint).noEmployeeConsolidationPointDTO();
            } catch (Exception e) {
                return "account already exists";
            }

        }
        return null;
    }

    @Override
    public ConsolidationPointDTO saveEdit(EditDTO editDTO) {
        Account account = iAccount.findById(editDTO.getIdAccount()).get();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        if (account.getPassword().equals(editDTO.getPassword())) {
        if (passwordEncoder.matches(editDTO.getPassword(), account.getPassword())) {
            ConsolidationPoint consolidationPoint = iConsolidationPointRepo.findById(editDTO.getId()).get();
            consolidationPoint.setName(editDTO.getName());
            return iConsolidationPointRepo.save(consolidationPoint).consolidationPointDTOLeader();
        }
        return null;
    }

    @Override
    public List<ConsolidationPoint> findAllByNotInAccountId(long accountId) {
        List<ConsolidationPoint> consolidationPointList = iConsolidationPointRepo.findAllByNotInAccountId(accountId);
        return consolidationPointList;
    }

    @Override
    public ConsolidationPoint findByTransactionPoint_AccountId(long accountId) {
        return iConsolidationPointRepo.findByTransactionPoint_AccountId(accountId);
    }

    @Override
    public ConsolidationPoint findConsolidationPointsByAccountId(long accountId) {
        return iConsolidationPointRepo.findConsolidationPointsByAccountId(accountId);
    }

    @Override
    public List<ConsolidationPoint> findAll() {
        return iConsolidationPointRepo.findAll();
    }
}
