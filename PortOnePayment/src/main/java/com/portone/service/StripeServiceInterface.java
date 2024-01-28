package com.portone.service;

import com.portone.model.PaymentIntentRequest;

public interface StripeServiceInterface {

	String createIntent(PaymentIntentRequest paymentIntentRequest);
	
	String captureIntent(String id);
	
	String createRefund(String id);
	
	String getIntents();
}
