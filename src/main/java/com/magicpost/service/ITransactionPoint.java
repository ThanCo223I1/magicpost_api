package com.magicpost.service;

import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateTransactionRequest;
import com.magicpost.model.dto.EditDTO;
import com.magicpost.model.dto.EditLeaderPoint;
import com.magicpost.model.dto.TransactionPointDTO;

import java.util.List;
import java.util.Optional;

public interface ITransactionPoint {
    List<TransactionPointDTO> findAllByStatus(int status);

    Optional<TransactionPoint> findById(long idTransactionPoint);

    TransactionPointDTO create(CreateTransactionRequest createTransactionRequest);

    TransactionPoint edit(TransactionPoint transactionPoint);
    TransactionPointDTO findByLeader_Id(long id);

    long findIdTransactionByIdEmployee(long idEmployee);
    TransactionPointDTO save(EditDTO editDTO);
    TransactionPointDTO saveStatus(long  id , int status);
    Object saveLeader(EditLeaderPoint editLeaderPoint);
}
