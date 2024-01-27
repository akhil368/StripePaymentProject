package com.portone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portone.service.PaymentIntentRequest;
import com.portone.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;


@RestController
@RequestMapping("/api/v1")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create_intent")
    public PaymentIntent createIntent(@RequestBody PaymentIntentRequest request) throws StripeException {
        return stripeService.createPaymentIntent(request.getAmount(), request.getCurrency());
    }

    @PostMapping("/capture_intent/{id}")
    public PaymentIntent captureIntent(@PathVariable String id) throws StripeException {
        return stripeService.capturePaymentIntent(id);
    }

//    @PostMapping("/create_refund/{id}")
//    public PaymentIntent createRefund(@PathVariable String id) throws StripeException {
//        return stripeService.createRefund(id);
//    }

    @GetMapping("/get_intents")
    public PaymentIntentCollection getIntents() throws StripeException {
        return stripeService.getAllPaymentIntents();
    }
}