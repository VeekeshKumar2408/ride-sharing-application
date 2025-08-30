package com.veekesh.project.uber.uberApp.services;

import com.veekesh.project.uber.uberApp.entities.Payment;
import com.veekesh.project.uber.uberApp.entities.Ride;
import com.veekesh.project.uber.uberApp.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
