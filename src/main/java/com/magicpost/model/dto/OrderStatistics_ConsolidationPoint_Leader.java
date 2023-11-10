package com.magicpost.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@NoArgsConstructor
@Getter
@Setter
public class OrderStatistics_ConsolidationPoint_Leader {
    private int year;
    private int month;
    private BigInteger totalOrders_month;

    public OrderStatistics_ConsolidationPoint_Leader(int year, int month, BigInteger totalOrders_month) {
        this.year = year;
        this.month = month;
        this.totalOrders_month = totalOrders_month;
    }
}
