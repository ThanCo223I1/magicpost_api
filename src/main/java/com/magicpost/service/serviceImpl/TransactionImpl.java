package com.magicpost.service.serviceImpl;

import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import com.magicpost.repo.ITransactionPointRepo;
import com.magicpost.service.ITransactionPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionImpl implements ITransactionPoint {
    ITransactionPointRepo transactionPointRepo;

    public TransactionImpl(ITransactionPointRepo transactionPointRepo) {
        this.transactionPointRepo = transactionPointRepo;
    }

    @Override
    public List<TransactionPoint> getAll() {
        return transactionPointRepo.findAll();
    }

    @Override
    public Optional<TransactionPoint> findById(long idTransactionPoint) {
        return transactionPointRepo.findById(idTransactionPoint);
    }

    @Override
    public TransactionPoint create(TransactionPoint transactionPoint , Leader leader) {
        transactionPoint.setLeader(leader);
        return transactionPointRepo.save(transactionPoint);
    }

    @Override
    public TransactionPoint edit(TransactionPoint transactionPoint) {
        return transactionPointRepo.save(transactionPoint);
    }
}
