package com.veekesh.project.uber.uberApp.dto;

import com.veekesh.project.uber.uberApp.enums.TransactionMethod;
import com.veekesh.project.uber.uberApp.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private LocalDateTime timeStamp;

    private WalletDto wallet;
}
