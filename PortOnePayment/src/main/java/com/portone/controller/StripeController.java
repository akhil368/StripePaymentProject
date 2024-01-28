package com.portone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portone.model.PaymentIntentRequest;
import com.portone.service.StripeServiceInterface;
import com.stripe.exception.StripeException;


@RestController
@RequestMapping("/api/v1")
public class StripeController {

    @Autowired
    private StripeServiceInterface stripeServiceInterface;

    
   

    @PostMapping("/create_intent")
    public ResponseEntity<String> createIntent(@RequestBody PaymentIntentRequest request) throws StripeException {
        return new ResponseEntity<>(stripeServiceInterface.createIntent(request),HttpStatus.CREATED);
    }

    @PostMapping("/capture_intent/{id}")
    public ResponseEntity<String> captureIntent(@PathVariable String id) throws StripeException {
        return new ResponseEntity<>(stripeServiceInterface.captureIntent(id),HttpStatus.OK);
    }

  @PostMapping("/create_refund/{id}")

    public ResponseEntity<String> createRefund(@PathVariable String id) throws StripeException {
      return new ResponseEntity<>( stripeServiceInterface.createRefund(id),HttpStatus.OK);

  	}

    @GetMapping("/get_intents")
    public ResponseEntity<String> getIntents() throws StripeException {
        return new ResponseEntity<>(stripeServiceInterface.getIntents(),HttpStatus.OK);
    }
}