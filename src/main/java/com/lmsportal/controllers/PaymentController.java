package com.lmsportal.controllers;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmsportal.models.Course;
import com.lmsportal.models.Enrollment;
import com.lmsportal.models.User;
import com.lmsportal.repositories.CourseRepository;
import com.lmsportal.repositories.EnrollmentRepository;
import com.lmsportal.repositories.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    @Value("${stripe.secretKey}")
    private String secretKey;
    
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(
            @RequestBody Map<String, String> body
    ) throws StripeException {

        String sessionId = body.get("sessionId");
        Stripe.apiKey = secretKey;
        Session session = Session.retrieve(sessionId);

        if (!"paid".equals(session.getPaymentStatus())) {
            return ResponseEntity.badRequest().body("Payment not completed");
        }

        Long courseId = Long.valueOf(session.getMetadata().get("courseId"));
        Long studentId = Long.valueOf(session.getMetadata().get("studentId"));

        Course course = courseRepo.findById(courseId).orElseThrow();
        User student = userRepo.findById(studentId).orElseThrow();

        // Prevent duplicate enrollment
        if (enrollmentRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            return ResponseEntity.ok("Already enrolled");
        }

        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .student(student)
                .enrolledAt(Instant.now())
                .amountPaid(session.getAmountTotal() / 100.0)
                .progressPercentage(0.0)
                .build();

        enrollmentRepo.save(enrollment);

        return ResponseEntity.ok("Enrollment successful");
    }
    
//    @GetMapping("/payment-success")
//    public ResponseEntity<?> success(@RequestParam("session_id") String sessionId) throws Exception {
//
//        Map<String,String> body = Map.of("sessionId", sessionId);
//        confirmPayment(body);
//
//        HttpHeaders headers = new HttpHeaders();
//        //headers.setLocation(URI.create("myapp://payment-success"));
//
//        headers.setLocation(URI.create("http://domain/#/payment-success"));
//        return new ResponseEntity<>(headers, HttpStatus.FOUND);
//    }



    
    @GetMapping("/payment-cancel")
    public String cancel() {
        return "Payment Cancelled";
    }

}
