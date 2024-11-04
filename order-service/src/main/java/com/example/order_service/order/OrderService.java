package com.example.order_service.order;

import com.example.order_service.customer.CustomerClient;
import com.example.order_service.exception.OrderException;
import com.example.order_service.kafka.OrderConfirmation;
import com.example.order_service.kafka.OrderProducer;
import com.example.order_service.orderline.OrderLine;
import com.example.order_service.orderline.OrderLineService;
import com.example.order_service.payment.PaymentRequest;
import com.example.order_service.product.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderProducer orderProducer;

    public OrderResponse createOrder(OrderRequest request) {

        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() ->
                        new OrderException("Customer id con id " + request.customerId() + " non esiste!"));

        // aggiorno il database dei prodotti scalando le quantità acquistate
        var purchasedProducts = productClient.purchaseProducts(request.purchaseRequests());
        // salvo l'ordine nel DB degli ordini
        Order order = orderRepository.save(orderMapper.toOrder(request));
        // aggiornare la tabella OrderLine
        request.purchaseRequests().forEach(p -> orderLineService.saveOrderLine(
                OrderLine
                        .builder()
                        .order(order)
                        .productId(p.productId())
                        .quantity(p.quantity())
                        .build()
        ));
        var paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMethod(request.paymentMethod())
                .reference(request.reference())
                .amount(request.amount())
                .customer(customer);

        // paymentClient.requestOrderPayment(paymentRequest)

        // inviare la conferma dell'ordine tramite Kafka
        orderProducer.sendOrderConfirmation(OrderConfirmation
                .builder()
                .reference(request.reference())
                .totalAmount(request.amount())
                .customer(customer)
                .paymentMethod(request.paymentMethod())
                .products(purchasedProducts)
                .build());

        return OrderResponse
                .builder()
                .amount(request.amount())
                .customerId(customer.id())
                .paymentMethod(request.paymentMethod())
                .reference(request.reference())
                .build();
    }
}
