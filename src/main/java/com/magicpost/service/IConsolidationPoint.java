package com.magicpost.service;

import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.dto.ConsolidationPointDTO;
import com.magicpost.model.dto.CreateConsolidationRequest;

import java.util.List;

public interface IConsolidationPoint {
    List<ConsolidationPointDTO> getAll();
    ConsolidationPointDTO create(CreateConsolidationRequest createConsolidationRequest);
    List<ConsolidationPoint> findAllByNotInAccountId(long accountId);
    ConsolidationPoint findByTransactionPoint_AccountId(long accountId);
}
