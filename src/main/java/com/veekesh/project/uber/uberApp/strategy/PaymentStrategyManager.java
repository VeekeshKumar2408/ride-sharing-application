package com.veekesh.project.uber.uberApp.strategy;

import com.veekesh.project.uber.uberApp.enums.PaymentMethod;
import com.veekesh.project.uber.uberApp.strategy.impl.CashPaymentStrategy;
import com.veekesh.project.uber.uberApp.strategy.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
            default -> throw new RuntimeException("Invalid payment method");
        };
    }
}
