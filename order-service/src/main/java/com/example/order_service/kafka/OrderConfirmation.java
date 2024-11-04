package com.example.order_service.kafka;

import com.example.order_service.customer.CustomerResponse;
import com.example.order_service.order.PaymentMethod;
import com.example.order_service.product.PurchaseResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderConfirmation(
        String reference,
        Double totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
