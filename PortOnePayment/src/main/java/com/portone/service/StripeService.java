package com.portone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class StripeService {

	 
	private String stripeSecretKey="sk_test_51Od2WBSJ0DfSokyzjCYIh4Dugc3s8q8ucn4jLASmBxTkKEMUlq6xfFHCeyvrbGgM0LXEBW4Q2sv9JLGSixZKJKDp00FqXVJppO";
	

    public StripeService() {
        Stripe.apiKey = stripeSecretKey;
    }
    

    public PaymentIntent createPaymentIntent(Long amount, String currency) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .build();
        return PaymentIntent.create(params);
    }

    public PaymentIntent capturePaymentIntent(String intentId) throws StripeException {
        PaymentIntentCaptureParams params = PaymentIntentCaptureParams.builder().build();
        return PaymentIntent.retrieve(intentId).capture(params);
    }
//
//    public PaymentIntent createRefund(String intentId) throws StripeException {
//        PaymentIntentRefundCreateParams params = PaymentIntentRefundCreateParams.builder().build();
//        return PaymentIntent.retrieve(intentId).createRefund(params);
//    }

    public PaymentIntentCollection getAllPaymentIntents() throws StripeException {
    	HashMap<String,Object> map=new HashMap<>();
        return   PaymentIntent.list(map);
    }
    
}
