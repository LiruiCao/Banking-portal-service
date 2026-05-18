package com.mycomany.bankingservice.payment.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.mycomany.bank.contract.model.TransferRequest;
import com.mycomany.bank.contract.model.TransferResponse;
import com.mycomany.bankingservice.payment.service.ETransferService;

@WebMvcTest(ETransferController.class)
class ETransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ETransferService eTransferService;

    @Test
    void submitTransferReturnsCreatedResponse() throws Exception {
        TransferResponse mockResponse = new TransferResponse()
                .transactionId("ETR-20260507-IJN06")
                .amount(99.00)
                .currency("CAD")
                .toName("Marcus Rivera")
                .sentDate(OffsetDateTime.parse("2026-05-07T16:29:32Z"))
                .fromAccount("Everyday Chequing ****4821")
                .userMessage("test sending");

        when(eTransferService.processTransfer(any(TransferRequest.class),anyString())).thenReturn(mockResponse);

        String requestJson = """
                {
                  "amount": 99.00,
                  "currency": "CAD",
                  "recipientId": "rec-mrivera-001",
                  "accountId": "acc-everyday-4821",
                  "userMessage": "test sending"
                }
                """;
        String mockedIdempotencyKey = "550e8400-e29b-41d4-a716-446655440000";
        mockMvc.perform(post("/api/v1/etransfer/execute")
                        .header("idempotencyKey", mockedIdempotencyKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionId").value("ETR-20260507-IJN06"))
                .andExpect(jsonPath("$.amount").value(99.0))
                .andExpect(jsonPath("$.currency").value("CAD"))
                .andExpect(jsonPath("$.toName").value("Marcus Rivera"))
                .andExpect(jsonPath("$.fromAccount").value("Everyday Chequing ****4821"))
                .andExpect(jsonPath("$.userMessage").value("test sending"));

        verify(eTransferService).processTransfer(any(TransferRequest.class),eq(mockedIdempotencyKey));
    }
}
