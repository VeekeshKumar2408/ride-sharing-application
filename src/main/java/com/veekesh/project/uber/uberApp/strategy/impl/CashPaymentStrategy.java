package com.veekesh.project.uber.uberApp.strategy.impl;

import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.Payment;
import com.veekesh.project.uber.uberApp.enums.PaymentStatus;
import com.veekesh.project.uber.uberApp.enums.TransactionMethod;
import com.veekesh.project.uber.uberApp.repositories.PaymentRepository;
import com.veekesh.project.uber.uberApp.services.PaymentService;
import com.veekesh.project.uber.uberApp.services.WalletService;
import com.veekesh.project.uber.uberApp.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission,
                null, payment.getRide(),
                TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);    }
}
