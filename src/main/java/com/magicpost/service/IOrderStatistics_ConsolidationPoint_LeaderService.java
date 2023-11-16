package com.magicpost.service;

import com.magicpost.model.dto.DateEndOrder_YearDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;

import java.util.List;

public interface IOrderStatistics_ConsolidationPoint_LeaderService {
    List<DateEndOrder_YearDTO> dateCreateOrder_Year();
    List<DateEndOrder_YearDTO> dateEndOrder_Year();
    List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusComplete(int year, long idAccount);
    List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusCancel(int year, long idAccount);
    List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusShipping(int year, long idAccount);
}
