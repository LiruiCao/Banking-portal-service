package com.mycomany.bankingservice.payment.service;

import com.mycomany.bank.contract.model.TransferRequest;
import com.mycomany.bank.contract.model.TransferResponse;

public interface ETransferService {
    TransferResponse processTransfer(TransferRequest request,String idempotencyKey);
}
