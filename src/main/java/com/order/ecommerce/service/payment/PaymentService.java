package com.order.ecommerce.service.payment;

import com.order.ecommerce.constants.PaymentMode;
import com.order.ecommerce.constants.PaymentStatus;
import com.order.ecommerce.mapper.PaymentsBuilder;
import com.order.ecommerce.model.Payment;
import com.order.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(double amount, PaymentMode paymentMode) {
        return paymentRepository.save(PaymentsBuilder
                .buildPayment(paymentMode, amount, PaymentStatus.PROCESSING));
    }
}

