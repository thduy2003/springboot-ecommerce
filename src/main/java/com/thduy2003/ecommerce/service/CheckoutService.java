package com.thduy2003.ecommerce.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.thduy2003.ecommerce.dto.PaymentInfo;
import com.thduy2003.ecommerce.dto.Purchase;
import com.thduy2003.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
