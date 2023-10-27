package com.magicpost.service;

import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;

import java.util.List;
import java.util.Optional;

public interface ITransactionPoint {
    List<TransactionPoint> getAll();

    Optional<TransactionPoint> findById(long idTransactionPoint);

    TransactionPoint create(TransactionPoint transactionPoint, Leader leader);

    TransactionPoint edit(TransactionPoint transactionPoint);
}
