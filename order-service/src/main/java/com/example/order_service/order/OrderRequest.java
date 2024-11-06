package com.example.order_service.order;

import com.example.order_service.payment.PaymentMethod;
import com.example.order_service.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Builder
@Validated
public record OrderRequest(
        String reference,
        @Positive(message = "Il prezzo non può essere negativo")
        Double amount,
        @NotNull(message = "Il metodo di pagamento deve essere specificato")
        PaymentMethod paymentMethod,
        @NotNull(message = "L'id del customer è obbligatorio")
        Long customerId,
        @NotEmpty(message = "Almeno un prodotto deve essere acquistato!")
        List<PurchaseRequest> purchaseRequests

) {



}
