package com.magicpost.controller.Account.transactionPointAccount;

import com.magicpost.model.dto.CreateTransactionRequest;
import com.magicpost.model.dto.EditDTO;
import com.magicpost.model.dto.EditLeaderPoint;
import com.magicpost.model.dto.TransactionPointDTO;
import com.magicpost.service.IOrderService;
import com.magicpost.service.ITransactionPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/transaction")
public class TransactionPointAccount {
    private final ITransactionPoint iTransactionPoint;
    private final IOrderService iOrderService;

    public TransactionPointAccount(ITransactionPoint iTransactionPoint, IOrderService iOrderService) {
        this.iTransactionPoint = iTransactionPoint;
        this.iOrderService = iOrderService;
    }

    @PostMapping("create")
    public ResponseEntity<TransactionPointDTO> create(@RequestBody CreateTransactionRequest transactionRequest) {
        return ResponseEntity.ok(iTransactionPoint.create(transactionRequest));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionPointDTO>> getAllActive() {
        iOrderService.getReceivedOrdersByConsolidationPoint();
        return ResponseEntity.ok(iTransactionPoint.findAllByStatus(1));
    }
    @GetMapping("block")
    public ResponseEntity<List<TransactionPointDTO>> getAllBlock() {
        return ResponseEntity.ok(iTransactionPoint.findAllByStatus(2));
    }

    @GetMapping("leader/{id}")
    public ResponseEntity<TransactionPointDTO> findByLeader_Id(@PathVariable long id) {
        return ResponseEntity.ok(iTransactionPoint.findByLeader_Id(id));
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody EditDTO editDTO) {
        TransactionPointDTO transactionPointDTO = iTransactionPoint.save(editDTO);
        if (transactionPointDTO != null) {
            return ResponseEntity.ok(transactionPointDTO);
        }
        return ResponseEntity.ok("wrong password");
    }
    @GetMapping("save/{id}/{status}")
    public ResponseEntity<?> saveStatus(@PathVariable long id , @PathVariable int status){
        iTransactionPoint.saveStatus(id,status);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("saveLeader")
    public ResponseEntity<?> saveLeader(@RequestBody EditLeaderPoint editLeaderPoint) {
        Object o = iTransactionPoint.saveLeader(editLeaderPoint);
        if (o == null) {
            return ResponseEntity.ok("wrong password");
        } else if (o.equals("account already exists")) {
            return ResponseEntity.ok("account already exists");
        } else {
            TransactionPointDTO transactionPointDTO = (TransactionPointDTO) o;
            return ResponseEntity.ok(transactionPointDTO);
        }

    }
}
