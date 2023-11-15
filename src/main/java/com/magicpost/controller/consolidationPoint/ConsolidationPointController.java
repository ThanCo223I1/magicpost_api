package com.magicpost.controller.consolidationPoint;

import com.magicpost.model.ConsolidationPoint;
import com.magicpost.service.IConsolidationPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/consolidationPoint")
public class ConsolidationPointController {
    @Autowired
    IConsolidationPoint iConsolidationPoint;

    @GetMapping("/findAll")
    public List<ConsolidationPoint> findAll() {
        return iConsolidationPoint.findAll();
    }

    @GetMapping("/notAccount/{idAccount}")
    public List<ConsolidationPoint> findAllByNotInAccountId(@PathVariable long idAccount) {
        return iConsolidationPoint.findAllByNotInAccountId(idAccount);
    }

    @GetMapping("/transactionPoint/account/{idAccount}")
    public ConsolidationPoint findByTransactionPoint_AccountId(@PathVariable long idAccount) {
        return iConsolidationPoint.findByTransactionPoint_AccountId(idAccount);
    }

    @GetMapping("/account/{idAccount}")
    public ConsolidationPoint findConsolidationPointsByAccountId(@PathVariable long idAccount) {
        return iConsolidationPoint.findConsolidationPointsByAccountId(idAccount);
    }
}
