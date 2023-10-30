package com.magicpost.model.dto;

import com.magicpost.model.Account;
import com.magicpost.model.ConsolidationPoint;
import com.magicpost.model.Leader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateConsolidationRequest {
    private Account account;
    private Leader leader;
    private ConsolidationPoint consolidationPoint;
}
