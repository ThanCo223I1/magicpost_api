package com.magicpost.controller.Account.transactionPointAccount;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;
import com.magicpost.model.TransactionPoint;
import com.magicpost.model.dto.CreateTransactionRequest;
import com.magicpost.model.dto.TransactionPointDTO;
import com.magicpost.service.ITransactionPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/transaction")
public class TransactionPointAccount {
    private final ITransactionPoint iTransactionPoint;

    public TransactionPointAccount(ITransactionPoint iTransactionPoint) {
        this.iTransactionPoint = iTransactionPoint;
    }

    @PostMapping("create")
    public ResponseEntity<TransactionPointDTO> create(@RequestBody CreateTransactionRequest transactionRequest){
        return ResponseEntity.ok(iTransactionPoint.create(transactionRequest));
    }
    @GetMapping()
    public ResponseEntity<List<TransactionPointDTO>> getAll(){
        return ResponseEntity.ok(iTransactionPoint.getAll());

    }
}
