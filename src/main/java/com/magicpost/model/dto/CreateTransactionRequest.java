package com.magicpost.model.dto;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTransactionRequest {
    private Account account;
    private Leader leader;
    private TransactionPoint transactionPoint;
    private ConsolidationPoint consolidationPoint;
}
