package com.magicpost.service;

import com.magicpost.model.dto.OrderStatistics_AdminDTO;

import java.util.List;

public interface IOrderStatistics_AdminDTO {
    List<OrderStatistics_AdminDTO> getOrderStatisticsByYear_IdTransaction_IdStatus(long idTransaction, long idStatus, int year);
    List<OrderStatistics_AdminDTO> getOrderStatisticsByYear_IdConsolidation_IdStatus(long idConsolidation, long idStatus, int year);
}
