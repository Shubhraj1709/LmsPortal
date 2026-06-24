package com.lmsportal.controllers;

import java.util.Map; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmsportal.dto.ProductRequest;
import com.lmsportal.dto.StripeResponse;
import com.lmsportal.repositories.EnrollmentRepository;
import com.lmsportal.services.StripeService;


@RestController
@RequestMapping("/api/product/v1")
public class ProductCheckoutController {

    private final StripeService stripeService;
    private final EnrollmentRepository enrollmentRepository;

    public ProductCheckoutController(
            StripeService stripeService,
            EnrollmentRepository enrollmentRepository) {

        this.stripeService = stripeService;
        this.enrollmentRepository = enrollmentRepository;
    }
    
    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutProducts(
            @RequestBody ProductRequest productRequest) {

        Long studentId = productRequest.getStudentId();
        Long courseId  = productRequest.getCourseId();

        // ✅ BLOCK DUPLICATE ENROLLMENT BEFORE STRIPE
        boolean alreadyEnrolled =
                enrollmentRepository.existsByStudent_IdAndCourse_Id(studentId, courseId);

        if (alreadyEnrolled) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Course is already enrolled"));
        }

        StripeResponse response = stripeService.checkoutProducts(productRequest);
        return ResponseEntity.ok(response);
    }

}

