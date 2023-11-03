package com.magicpost.service;

import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.dto.*;

import java.util.List;

public interface IConsolidationPoint {
    List<ConsolidationPointDTO> findAllByStatus(int status);
    ConsolidationPointDTO create(CreateConsolidationRequest createConsolidationRequest);
    ConsolidationPoint findById(long id);
    ConsolidationPoint save(ConsolidationPoint consolidationPoint);
    ConsolidationPointDTO  findByLeader_Id(long id);

    long findIdConsolidationByEmployee(long idEmployee);
    ConsolidationPointDTO saveStatus(long  id , int status);
    Object saveLeader(EditLeaderPoint editLeaderPoint);
    ConsolidationPointDTO saveEdit(EditDTO editDTO);


    List<ConsolidationPoint> findAllByNotInAccountId(long accountId);
    ConsolidationPoint findByTransactionPoint_AccountId(long accountId);
}
