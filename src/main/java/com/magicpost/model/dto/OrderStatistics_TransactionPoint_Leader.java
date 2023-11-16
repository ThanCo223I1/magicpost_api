package com.magicpost.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderStatistics_TransactionPoint_Leader {
    private int year;
    private int month;
    private BigInteger totalOrders_month;
}
