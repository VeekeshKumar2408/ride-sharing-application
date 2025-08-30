package com.veekesh.project.uber.uberApp.services;

import com.veekesh.project.uber.uberApp.entities.Ride;
import com.veekesh.project.uber.uberApp.entities.User;
import com.veekesh.project.uber.uberApp.entities.Wallet;
import com.veekesh.project.uber.uberApp.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount , String transactionId, Ride ride, TransactionMethod transactionMethod);
    void withDrawAllMyMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
