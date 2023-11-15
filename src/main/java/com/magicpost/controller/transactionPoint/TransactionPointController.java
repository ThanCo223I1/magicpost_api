package com.magicpost.controller.transactionPoint;

import com.magicpost.model.TransactionPoint;
import com.magicpost.service.ITransactionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/transactionPoint")
public class TransactionPointController {
    @Autowired
    ITransactionPoint iTransactionPoint;

    @GetMapping("/findAll")
    public List<TransactionPoint> findAll() {
        return iTransactionPoint.findAll();
    }

    @GetMapping("/account/{idAccount}")
    public TransactionPoint findTransactionPointsByAccountId(@PathVariable long idAccount) {
        return iTransactionPoint.findTransactionPointsByAccountId(idAccount);
    }
}
