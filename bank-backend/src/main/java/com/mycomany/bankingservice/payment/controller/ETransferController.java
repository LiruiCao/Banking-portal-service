package com.mycomany.bankingservice.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycomany.bank.contract.model.TransferRequest;
import com.mycomany.bank.contract.model.TransferResponse;
import com.mycomany.bankingservice.payment.service.ETransferService;

@RestController
//config base path for the controller
@RequestMapping("/api/v1/etransfer")
public class ETransferController {
    private final ETransferService eTransferService;

    public ETransferController(ETransferService eTransferService) {
        this.eTransferService = eTransferService;
    }

    //endpoint to execute the e-transfer
    @PostMapping("/execute")
    public ResponseEntity<TransferResponse> submitTransfer(@RequestBody TransferRequest request
    , @RequestHeader("idempotencyKey") String idempotencyKey) {
        TransferResponse response = eTransferService.processTransfer(request,idempotencyKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
