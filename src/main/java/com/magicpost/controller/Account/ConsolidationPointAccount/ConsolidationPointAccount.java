package com.magicpost.controller.Account.ConsolidationPointAccount;

import com.magicpost.model.dto.*;
import com.magicpost.service.IConsolidationPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account/consolidation")
public class ConsolidationPointAccount {
    private final IConsolidationPoint iConsolidationPoint;

    public ConsolidationPointAccount(IConsolidationPoint iConsolidationPoint) {
        this.iConsolidationPoint = iConsolidationPoint;
    }
    @GetMapping()
    public ResponseEntity<List<ConsolidationPointDTO>> getAllActive(){
        return ResponseEntity.ok(iConsolidationPoint.findAllByStatus(1));
    }
    @GetMapping("block")
    public ResponseEntity<List<ConsolidationPointDTO>> getAllBlock(){
        return ResponseEntity.ok(iConsolidationPoint.findAllByStatus(2));
    }
    @GetMapping("save/{id}/{status}")
    public ResponseEntity<?> saveStatus(@PathVariable long id , @PathVariable int status){
        iConsolidationPoint.saveStatus(id,status);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("create")
    public ResponseEntity<ConsolidationPointDTO> create(@RequestBody CreateConsolidationRequest createConsolidationRequest){
        return ResponseEntity.ok(iConsolidationPoint.create(createConsolidationRequest));
    }
    @GetMapping("leader/{id}")
    public ResponseEntity<ConsolidationPointDTO> findByLeader_Id(@PathVariable long id){
        return ResponseEntity.ok(iConsolidationPoint.findByLeader_Id(id));

    }
    @PostMapping("saveLeader")
    public ResponseEntity<?> saveLeader(@RequestBody EditLeaderPoint editLeaderPoint) {
        Object o = iConsolidationPoint.saveLeader(editLeaderPoint);
        if (o == null) {
            return ResponseEntity.ok("wrong password");
        } else if (o.equals("account already exists")) {
            return ResponseEntity.ok("account already exists");
        } else {
            ConsolidationPointDTO consolidationPointDTO = (ConsolidationPointDTO) o;
            return ResponseEntity.ok(consolidationPointDTO);
        }

    }
    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody EditDTO editDTO) {
        ConsolidationPointDTO consolidationPointDTO = iConsolidationPoint.saveEdit(editDTO);
        if (consolidationPointDTO != null) {
            return ResponseEntity.ok(consolidationPointDTO);
        }
        return ResponseEntity.ok("wrong password");
    }
}
