package com.order.ecommerce.mapper;

import com.order.ecommerce.constants.PaymentMode;
import com.order.ecommerce.constants.PaymentStatus;
import com.order.ecommerce.model.Payment;

public class PaymentsBuilder {

    public static Payment buildPayment(PaymentMode paymentMode,
                                       double amount,
                                       PaymentStatus paymentStatus) {
        return Payment.builder().amount(amount)
                .paymentMode(paymentMode)
                .paymentStatus(paymentStatus.name())
                .build();
    }
}
