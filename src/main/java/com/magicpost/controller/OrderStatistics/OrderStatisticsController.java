package com.magicpost.controller.OrderStatistics;

import com.magicpost.model.dto.DateEndOrder_YearDTO;
import com.magicpost.model.dto.OrderStatistics_AdminDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;
import com.magicpost.service.IOrderStatistics_AdminDTO;
import com.magicpost.service.IOrderStatistics_ConsolidationPoint_LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderStatistics")
public class OrderStatisticsController {
    @Autowired
    IOrderStatistics_ConsolidationPoint_LeaderService iOrderStatisticsConsolidationPointLeaderService;
    @Autowired
    IOrderStatistics_AdminDTO iOrderStatisticsAdminDTO;

    @GetMapping("/consolidationPoint_Leader_StatusComplete/{idAccount}/year/{year}")
    public List<OrderStatistics_ConsolidationPoint_Leader> orderStatistics_ConsolidationPoint_Leader_StatusComplete(@PathVariable int year, @PathVariable long idAccount) {
        return iOrderStatisticsConsolidationPointLeaderService.getOrderStatisticsByYearAndAccountId_StatusComplete(year, idAccount);
    }

    @GetMapping("/consolidationPoint_Leader_StatusCancel/{idAccount}/year/{year}")
    public List<OrderStatistics_ConsolidationPoint_Leader> orderStatistics_ConsolidationPoint_Leader_StatusCancel(@PathVariable int year, @PathVariable long idAccount) {
        return iOrderStatisticsConsolidationPointLeaderService.getOrderStatisticsByYearAndAccountId_StatusCancel(year, idAccount);
    }

    @GetMapping("/consolidationPoint_Leader_StatusShipping/{idAccount}/year/{year}")
    public List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_StatusShipping(@PathVariable int year, @PathVariable long idAccount) {
        return iOrderStatisticsConsolidationPointLeaderService.getOrderStatisticsByYearAndAccountId_StatusShipping(year, idAccount);
    }

    @GetMapping("/dateEndOrder_Year")
    public List<DateEndOrder_YearDTO> dateEndOrder_Year() {
        return iOrderStatisticsConsolidationPointLeaderService.dateEndOrder_Year();
    }

    @GetMapping("/dateCreateOrder_Year")
    public List<DateEndOrder_YearDTO> dateCreateOrder_Year() {
        return iOrderStatisticsConsolidationPointLeaderService.dateCreateOrder_Year();
    }

    @GetMapping("/admin/transactionPoint/{idTransaction}/year/{year}/status/{idStatus}")
    public List<OrderStatistics_AdminDTO> getOrderStatisticsByYear_IdTransaction_IdStatus(@PathVariable long idTransaction, @PathVariable long idStatus, @PathVariable int year) {
        return iOrderStatisticsAdminDTO.getOrderStatisticsByYear_IdTransaction_IdStatus(idTransaction, idStatus, year);
    }

    @GetMapping("/admin/consolidationPoint/{idConsolidation}/year/{year}/status/{idStatus}")
    public List<OrderStatistics_AdminDTO> getOrderStatisticsByYear_IdConsolidation_IdStatus(@PathVariable long idConsolidation, @PathVariable long idStatus, @PathVariable int year) {
        return iOrderStatisticsAdminDTO.getOrderStatisticsByYear_IdConsolidation_IdStatus(idConsolidation, idStatus, year);
    }

    @GetMapping("/admin/shipped/year/{year}/status/{idStatus}")
    public List<OrderStatistics_AdminDTO> getOrderStatisticsEndOrderByYear_IdStatus(@PathVariable long idStatus, @PathVariable int year) {
        return iOrderStatisticsAdminDTO.getOrderStatisticsEndOrderByYear_IdStatus(idStatus, year);
    }

    @GetMapping("/admin/inventory/year/{year}")
    public List<OrderStatistics_AdminDTO> getOrderStatisticsCreateOrderByYear_IdStatus(@PathVariable int year) {
        return iOrderStatisticsAdminDTO.getOrderStatisticsCreateOrderByYear_IdStatus(year);
    }
}
