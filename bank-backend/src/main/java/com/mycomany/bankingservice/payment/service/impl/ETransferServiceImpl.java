package com.mycomany.bankingservice.payment.service.impl;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.mycomany.bank.contract.model.TransferRequest;
import com.mycomany.bank.contract.model.TransferResponse;
import com.mycomany.bankingservice.payment.service.ETransferService;

@Service
public class ETransferServiceImpl implements ETransferService {

    @Override
    public TransferResponse processTransfer(TransferRequest request) {
        OffsetDateTime sentDate = OffsetDateTime.parse("2026-05-08T10:15:30+00:00");
        return new TransferResponse()
                .transactionId("txn-789")
                .amount(request.getAmount())
                .currency(request.getCurrency() != null ? request.getCurrency() : "CAD")
                .toName("Jane Doe")
                .sentDate(sentDate)
                .fromAccount(request.getAccountId())
                .userMessage(request.getUserMessage());
    }
}
