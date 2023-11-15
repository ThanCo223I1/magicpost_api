package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateTransactionRequest;
import com.magicpost.model.dto.EditDTO;
import com.magicpost.model.dto.EditLeaderPoint;
import com.magicpost.model.dto.TransactionPointDTO;
import com.magicpost.repo.ITransactionPointRepo;
import com.magicpost.service.IAccount;
import com.magicpost.service.ILeader;
import com.magicpost.service.ITransactionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionImpl implements ITransactionPoint {
    @Autowired
    ITransactionPointRepo iTransactionPointRepo;
    IAccount iAccount;
    ILeader iLeader;

    public TransactionImpl(ITransactionPointRepo iTransactionPointRepo, IAccount iAccount, ILeader iLeader) {
        this.iTransactionPointRepo = iTransactionPointRepo;
        this.iAccount = iAccount;
        this.iLeader = iLeader;
    }

    @Override
    public List<TransactionPointDTO> findAllByStatus(int status) {
        List<TransactionPointDTO> transactionPointDTOList = new ArrayList<>();
        List<TransactionPoint> transactionPointList = iTransactionPointRepo.findAllByStatus(status);
        for (TransactionPoint t : transactionPointList) {
            transactionPointDTOList.add(t.transactionPointDTO());
        }
        return transactionPointDTOList;
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

    @Override
    public long findIdTransactionByIdEmployee(long idEmployee) {
        return iTransactionPointRepo.findIdTransactionByIdEmployee(idEmployee);
    }
    public TransactionPointDTO save(EditDTO editDTO) {
        Account account = iAccount.findById(editDTO.getIdAccount()).get();
        if (account.getPassword().equals(editDTO.getPassword())) {
            TransactionPoint transactionPoint = iTransactionPointRepo.findById(editDTO.getId()).get();
            transactionPoint.setName(editDTO.getName());
            return iTransactionPointRepo.save(transactionPoint).transactionPointDTO();
        }
        return null;
    }

    @Override
    public TransactionPointDTO saveStatus(long id, int status) {
        TransactionPoint transactionPoint = iTransactionPointRepo.findById(id).get();
        transactionPoint.setStatus(status);
        return iTransactionPointRepo.save(transactionPoint).transactionPointDTO();
    }

    @Override
    public Object saveLeader(EditLeaderPoint editLeaderPoint) {
        Account account = iAccount.findById(editLeaderPoint.getIdAccount()).get();
        if (account.getPassword().equals(editLeaderPoint.getPassword())) {
            Account newAccount;
            try {
               newAccount = iAccount.create(editLeaderPoint.getAccount());
                Leader leader = editLeaderPoint.getLeader();
                leader = iLeader.create(newAccount, leader);
                TransactionPoint transactionPoint = iTransactionPointRepo.findById(editLeaderPoint.getId()).get();
                transactionPoint.setLeader(leader);
                return iTransactionPointRepo.save(transactionPoint).transactionPointDTO();
            }catch (Exception e){
                return "account already exists";
            }

        }
        return null;
    }

    @Override
    public TransactionPoint findTransactionPointsByAccountId(long accountId) {
        return iTransactionPointRepo.findTransactionPointsByAccountId(accountId);
    }

    @Override
    public List<TransactionPoint> findAll() {
        return iTransactionPointRepo.findAll();
    }
}
