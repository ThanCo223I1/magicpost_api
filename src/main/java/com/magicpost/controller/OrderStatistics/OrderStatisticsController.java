package com.magicpost.controller.OrderStatistics;

import com.magicpost.model.dto.DateEndOrder_YearDTO;
import com.magicpost.model.dto.OrderStatistics_AdminDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;
import com.magicpost.model.dto.OrderStatistics_TransactionPoint_Leader;
import com.magicpost.service.IOrderStatistics_AdminDTO;
import com.magicpost.service.IOrderStatistics_ConsolidationPoint_LeaderService;
import com.magicpost.service.IOrderStatistics_TransactionPoint_LeaderService;
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
    IOrderStatistics_TransactionPoint_LeaderService iOrderStatisticsTransactionPointLeaderService;
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

    @GetMapping("/consolidationPoint_Leader_Received/{idAccount}/year/{year}")
    public List<OrderStatistics_ConsolidationPoint_Leader> getOrderStatisticsByYearAndAccountId_Received(@PathVariable int year, @PathVariable long idAccount) {
        return iOrderStatisticsConsolidationPointLeaderService.getOrderStatisticsByYearAndAccountId_Received(year, idAccount);
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

    @GetMapping("/transactionPoint_Leader/{idAccount}/status/{idStatus}/year/{year}")
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsEndOrderByYear_IdStatus(@PathVariable long idAccount, @PathVariable long idStatus, @PathVariable int year) {
        return iOrderStatisticsTransactionPointLeaderService.getOrderStatisticsEndOrderByYear_IdStatus(idAccount, idStatus, year);
    }

    @GetMapping("/transactionPoint_Leader/unsent/{idAccount}/year/{year}")
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_IdStatus_Unsent(@PathVariable long idAccount, @PathVariable int year) {
        return iOrderStatisticsTransactionPointLeaderService.getOrderStatisticsCreateOrderByYear_IdStatus_Unsent(idAccount, year);
    }

    @GetMapping("/transactionPoint_Leader/received/{idAccount}/year/{year}")
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_IdStatus_Received(@PathVariable long idAccount, @PathVariable int year) {
        return iOrderStatisticsTransactionPointLeaderService.getOrderStatisticsCreateOrderByYear_IdStatus_Received(idAccount, year);
    }

    @GetMapping("/transactionPoint_Leader/sent/{idAccount}/year/{year}")
    public List<OrderStatistics_TransactionPoint_Leader> getOrderStatisticsCreateOrderByYear_Sent(@PathVariable long idAccount, @PathVariable int year) {
        return iOrderStatisticsTransactionPointLeaderService.getOrderStatisticsCreateOrderByYear_Sent(idAccount, year);
    }
}
