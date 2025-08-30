package com.veekesh.project.uber.uberApp.strategy;

import com.veekesh.project.uber.uberApp.entities.Payment;
import lombok.Data;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
