package com.veekesh.project.uber.uberApp.dto;

import lombok.Data;

import java.util.Set;

@Data
public class WalletDto {
    private Long id;

    private UserDto user;

    private Double balance;

    private Set<WalletTransactionDto> transactions;

}
