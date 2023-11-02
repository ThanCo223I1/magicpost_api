package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateTransactionRequest;
import com.magicpost.model.dto.TransactionPointDTO;
import com.magicpost.repo.ITransactionPointRepo;
import com.magicpost.service.IAccount;
import com.magicpost.service.ILeader;
import com.magicpost.service.ITransactionPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionImpl implements ITransactionPoint {
    ITransactionPointRepo iTransactionPointRepo;
    IAccount iAccount;
    ILeader iLeader;

    public TransactionImpl(ITransactionPointRepo iTransactionPointRepo, IAccount iAccount, ILeader iLeader) {
        this.iTransactionPointRepo = iTransactionPointRepo;
        this.iAccount = iAccount;
        this.iLeader = iLeader;
    }

    @Override
    public List<TransactionPointDTO> getAll() {
        List<TransactionPointDTO> transactionPointDTOList = new ArrayList<>();
        List<TransactionPoint> transactionPointList = iTransactionPointRepo.findAll();
        for (TransactionPoint t: transactionPointList) {
            transactionPointDTOList.add(t.transactionPointDTO());
        }
        return transactionPointDTOList ;
    }

    @Override
    public Optional<TransactionPoint> findById(long idTransactionPoint) {
        return iTransactionPointRepo.findById(idTransactionPoint);
    }

    @Override
    public TransactionPointDTO create(CreateTransactionRequest createTransactionRequest) {
        Account account = createTransactionRequest.getAccount();
        Leader leader = createTransactionRequest.getLeader();
        TransactionPoint transactionPoint = createTransactionRequest.getTransactionPoint();
        iAccount.create(account);
        leader = iLeader.create(account, leader);
        transactionPoint.setLeader(leader);
        transactionPoint.setConsolidationPoint(createTransactionRequest.getConsolidationPoint());
        iTransactionPointRepo.save(transactionPoint);
        return transactionPoint.noEmployeeTransactionPointDTO();
    }


    @Override
    public TransactionPoint edit(TransactionPoint transactionPoint) {
        return iTransactionPointRepo.save(transactionPoint);
    }

    @Override
    public TransactionPointDTO findByLeader_Id(long id) {
        return iTransactionPointRepo.findByLeader_Id(id).transactionPointDTO();
    }
}
