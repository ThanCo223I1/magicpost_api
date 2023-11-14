package com.magicpost.controller.OrderStatistics;

import com.magicpost.model.dto.DateEndOrder_YearDTO;
import com.magicpost.model.dto.OrderStatistics_ConsolidationPoint_Leader;
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

    @GetMapping("/consolidationPoint_Leader_StatusComplete/{idAccount}/year/{year}")
    public List<OrderStatistics_ConsolidationPoint_Leader> orderStatistics_ConsolidationPoint_Leader_StatusComplete(@PathVariable int year, @PathVariable long idAccount) {
        return iOrderStatisticsConsolidationPointLeaderService.getOrderStatisticsByYearAndAccountId_StatusComplete(year, idAccount);
    }

    @GetMapping("/consolidationPoint_Leader_StatusCancel/{idAccount}/year/{year}")
    public List<OrderStatistics_ConsolidationPoint_Leader> orderStatistics_ConsolidationPoint_Leader_StatusCancel(@PathVariable int year, @PathVariable long idAccount) {
        return iOrderStatisticsConsolidationPointLeaderService.getOrderStatisticsByYearAndAccountId_StatusCancel(year, idAccount);
    }

    @GetMapping("/dateEndOrder_Year")
    public List<DateEndOrder_YearDTO> dateEndOrder_Year() {
        return iOrderStatisticsConsolidationPointLeaderService.dateEndOrder_Year();
    }
}
