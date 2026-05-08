package com.mycomany.bankingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.mycomany.bank.contract.model.TransferRequest;
import com.mycomany.bank.contract.model.TransferResponse;


public class TransferRequestAndTransferResponseTests {
    //test if we can create a TransferRequest object
    @Test
    void canCreateTransferRequest() {
        TransferRequest request = new TransferRequest()
                .amount(100.50)
                .currency("CAD")
                .recipientId("recipient-123")
                .accountId("account-456")
                .userMessage("Rent payment");

        assertNotNull(request);
        assertEquals(100.50, request.getAmount());
        assertEquals("CAD", request.getCurrency());
        assertEquals("recipient-123", request.getRecipientId());
        assertEquals("account-456", request.getAccountId());
        assertEquals("Rent payment", request.getUserMessage());
    }

    //test if we can create a TransferResponse object
    @Test
    void canCreateTransferResponse() {
        OffsetDateTime sentDate = OffsetDateTime.parse("2026-05-08T10:15:30+00:00");

        TransferResponse response = new TransferResponse()
                .transactionId("txn-789")
                .amount(100.50)
                .currency("CAD")
                .toName("Jane Doe")
                .sentDate(sentDate)
                .fromAccount("account-456")
                .userMessage("Rent payment");

        assertNotNull(response);
        assertEquals("txn-789", response.getTransactionId());
        assertEquals(100.50, response.getAmount());
        assertEquals("CAD", response.getCurrency());
        assertEquals("Jane Doe", response.getToName());
        assertEquals(sentDate, response.getSentDate());
        assertEquals("account-456", response.getFromAccount());
        assertEquals("Rent payment", response.getUserMessage());
    }
}
