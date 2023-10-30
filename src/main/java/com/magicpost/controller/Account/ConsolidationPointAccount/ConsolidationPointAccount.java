package com.magicpost.controller.Account.ConsolidationPointAccount;

import com.magicpost.model.dto.ConsolidationPointDTO;
import com.magicpost.model.dto.CreateConsolidationRequest;
import com.magicpost.service.IConsolidationPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/leader/consolidation")
public class ConsolidationPointAccount {
    private final IConsolidationPoint iConsolidationPoint;

    public ConsolidationPointAccount(IConsolidationPoint iConsolidationPoint) {
        this.iConsolidationPoint = iConsolidationPoint;
    }
    @GetMapping()
    public ResponseEntity<List<ConsolidationPointDTO>> getAll(){
        return ResponseEntity.ok(iConsolidationPoint.getAll());
    }
    @PostMapping("create")
    public ResponseEntity<ConsolidationPointDTO> create(@RequestBody CreateConsolidationRequest createConsolidationRequest){
        return ResponseEntity.ok(iConsolidationPoint.create(createConsolidationRequest));
    }
}