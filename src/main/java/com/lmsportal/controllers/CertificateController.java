package com.lmsportal.controllers;

import com.lmsportal.dto.CertificateResponseDTO;
import com.lmsportal.models.Certificate;
import com.lmsportal.models.User;
import com.lmsportal.repositories.UserRepository;
import com.lmsportal.services.CertificateService;
import com.lmsportal.utils.CertificateEligibilityChecker;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;
    private final CertificateEligibilityChecker eligibilityChecker;
    private final UserRepository userRepository;

    private User getCurrentUser(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping("/generate/{courseId}")
    public ResponseEntity<?> generateCertificate(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long courseId) {

        User user = getCurrentUser(userDetails);

        return ResponseEntity.ok(
                certificateService.generateCertificate(user.getId(), courseId)
        );
    }

    @GetMapping("/download/{courseId}")
    public ResponseEntity<byte[]> downloadCertificate(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long courseId) {

        User user = getCurrentUser(userDetails);

        byte[] pdf = certificateService.downloadCertificate(user.getId(), courseId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=certificate.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/eligibility/{courseId}")
    public ResponseEntity<?> checkEligibility(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long courseId) {

        User user = getCurrentUser(userDetails);

        try {
            eligibilityChecker.checkEligibility(user.getId(), courseId);
            return ResponseEntity.ok(Map.of(
                    "eligible", true,
                    "message", "Student is eligible for certificate"
            ));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "eligible", false,
                    "message", ex.getMessage()
            ));
        }
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCertificateDetails(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long courseId) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Certificate certificate =
                certificateService.getCertificate(user.getId(), courseId);

        return ResponseEntity.ok(
                new CertificateResponseDTO(
                        certificate.getCertificateCode(),
                        certificate.getStudent().getName(),
                        certificate.getCourse().getTitle(),
                        certificate.getIssueDate(),
                        certificate.getStatus().name()
                )
        );
    }

}
