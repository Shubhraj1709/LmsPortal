package com.lmsportal.services;

import com.lmsportal.enums.CertificateStatus;
import com.lmsportal.models.Certificate;
import com.lmsportal.models.Course;
import com.lmsportal.models.User;
import com.lmsportal.repositories.CertificateRepository;
import com.lmsportal.repositories.CourseRepository;
import com.lmsportal.repositories.UserRepository;
import com.lmsportal.services.CertificateService;
import com.lmsportal.utils.CertificateEligibilityChecker;
import com.lmsportal.utils.CertificatePdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final CertificateEligibilityChecker eligibilityChecker;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CertificatePdfGenerator pdfGenerator;

    @Override
    public Certificate generateCertificate(Long studentId, Long courseId) {

        // ELIGIBILITY CHECK
        eligibilityChecker.checkEligibility(studentId, courseId);

        certificateRepository.findByStudentIdAndCourseId(studentId, courseId)
                .ifPresent(c -> {
                    throw new RuntimeException("Certificate already issued");
                });

        Certificate certificate = Certificate.builder()
                .certificateCode(UUID.randomUUID().toString())
                .student(userRepository.findById(studentId).orElseThrow())
                .course(courseRepository.findById(courseId).orElseThrow())
                .issueDate(LocalDateTime.now())
                .status(CertificateStatus.ISSUED)
                .build();

        return certificateRepository.save(certificate);
    }
    
    @Override
    public Certificate getCertificate(Long studentId, Long courseId) {
        return certificateRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public byte[] downloadCertificate(Long studentId, Long courseId) {
        Certificate cert = getCertificate(studentId, courseId);
        return pdfGenerator.generate(cert);
    }
}
