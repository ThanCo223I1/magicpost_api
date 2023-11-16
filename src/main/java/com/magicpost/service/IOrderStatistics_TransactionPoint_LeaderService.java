package com.magicpost.service;

import com.magicpost.model.dto.OrderStatistics_TransactionPoint_Leader;

import java.util.List;

public interface IOrderStatistics_TransactionPoint_LeaderService {
    List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsEndOrderByYear_IdStatus(long idAccount, long idStatus, int year);
    List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_IdStatus_Unsent(long idAccount, int year);
    List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_IdStatus_Received(long idAccount, int year);
    List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_Sent(long idAccount, int year);
}
