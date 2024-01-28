package com.portone.service;

import java.util.HashMap;

import java.util.Map;


import org.springframework.stereotype.Service;

import com.portone.model.PaymentIntentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class StripeServiceImpl implements StripeServiceInterface{


	private String stripeSecretKey="sk_test_51Od2WBSJ0DfSokyzjCYIh4Dugc3s8q8ucn4jLASmBxTkKEMUlq6xfFHCeyvrbGgM0LXEBW4Q2sv9JLGSixZKJKDp00FqXVJppO";
	 

	public String getStripeSecretKey() {
		return stripeSecretKey;
	}






	/**
     * Create a payment intent.
     *
     * @param PaymentIntentDetails  including amount and currency.
     * @return String containing the JSON representation of the created PaymentIntent .
     */

	@Override
	public String createIntent(PaymentIntentRequest paymentIntentRequest) {
		// TODO Auto-generated method stub
		try {
			Stripe.apiKey=stripeSecretKey;
			PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(paymentIntentRequest.getAmount())
                            .setCurrency(paymentIntentRequest.getCurrency())
                            .build();
			
			PaymentIntent paymentIntent = PaymentIntent.create(params);
			return paymentIntent.toString();
		} catch (StripeException ex) {
			// TODO: handle exception
			return "Error in creating Payment Intent";
		}
	
	}

	/**
     * Capture a payment intent with the  ID.
     *
     * @param id The ID of the PaymentIntent to capture.
     * @return String containing the JSON representation of the captured PaymentIntent or an error response.
     */
	@Override
	public String captureIntent(String id) {
		
		try {
			Stripe.apiKey=stripeSecretKey;
			
			PaymentIntent pId=PaymentIntent.retrieve(id);
			
			PaymentIntentCaptureParams params = PaymentIntentCaptureParams.builder().build();
			
			PaymentIntent paymentIntent = pId.capture(params);
			
			return paymentIntent.toString();
		} catch (StripeException e) {
			return "Error in Capturing Intent"+ id ;
		}
		
	}

	

	/**
     * Create a refund for a payment intent with the  ID.
     *
     * @param id The ID of the PaymentIntent for which to create a refund.
     * @return String containing the JSON representation of the refunded PaymentIntent or an error response.
     */

	@Override
	public String createRefund(String id) {
		try {
			Stripe.apiKey=stripeSecretKey;
			
			PaymentIntent pId = PaymentIntent.retrieve(id);
			
			PaymentIntent refund=pId.cancel();
			return refund.toString();
		} catch (StripeException e) {
			return "Error while creating refund"+ id;
		}
		
	
	}

	/**
     * Get a list of PaymentIntents.
     *
     * @return String containing the JSON representation of the PaymentIntents or an error response.
     */

	@Override
	public String getIntents() {
		try {
			Stripe.apiKey=stripeSecretKey;
			
			Map<String,Object> map=new HashMap<>();
			
			
			PaymentIntentCollection paymentIntents=PaymentIntent.list(map);
			return  paymentIntents.toString();
		} catch (StripeException e) {
			return "Error while fetching Payment Intents";
		}

    
	}
}
