package com.lmsportal.services;

import com.lmsportal.dto.ProductRequest;
import com.lmsportal.dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    public StripeResponse checkoutProducts(ProductRequest productRequest) {

        Stripe.apiKey = secretKey;

        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productRequest.getName())
                        .build();

        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(productRequest.getCurrency() != null ? productRequest.getCurrency() : "INR")
                        .setUnitAmount(productRequest.getAmount())
                        .setProductData(productData)
                        .build();

        SessionCreateParams.LineItem lineItem =
                SessionCreateParams.LineItem.builder()
                        .setQuantity(productRequest.getQuantity())
                        .setPriceData(priceData)
                        .build();

        SessionCreateParams params =
        	    SessionCreateParams.builder()
        	        .setMode(SessionCreateParams.Mode.PAYMENT)
        	        .setSuccessUrl("http://localhost:55045/#/payment-success?session_id={CHECKOUT_SESSION_ID}")

        	        
//        	        .setSuccessUrl("http://localhost:3000/payment-success?session_id={CHECKOUT_SESSION_ID}")
//        	        .setCancelUrl("http://localhost:3000/payment-cancel")

//        	        .setCancelUrl("http://10.55.141.118:8080/payment-cancel")
        	        
        	        .setCancelUrl("http://localhost:55045/#/payment-cancel")

        	        .putMetadata("courseId", productRequest.getCourseId().toString())
        	        .putMetadata("studentId", productRequest.getStudentId().toString())
        	        .addLineItem(lineItem)
        	        .build();

        try {
            Session session = Session.create(params);

            return StripeResponse.builder()
                    .status("SUCCESS")
                    .message("Stripe checkout session created")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();

        } catch (StripeException e) {
            return StripeResponse.builder()
                    .status("FAILED")
                    .message(e.getMessage())
                    .build();
        }
    }
}
