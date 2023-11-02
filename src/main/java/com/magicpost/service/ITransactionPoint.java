package com.magicpost.service;

import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateTransactionRequest;
import com.magicpost.model.dto.TransactionPointDTO;

import java.util.List;
import java.util.Optional;

public interface ITransactionPoint {
    List<TransactionPointDTO> getAll();

    Optional<TransactionPoint> findById(long idTransactionPoint);

    TransactionPointDTO create(CreateTransactionRequest createTransactionRequest);

    TransactionPoint edit(TransactionPoint transactionPoint);
    TransactionPointDTO findByLeader_Id(long id);
}
