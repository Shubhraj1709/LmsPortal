package com.lmsportal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmsportal.models.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	Optional<Certificate> findByStudentIdAndCourseId(Long studentId, Long courseId);

    Optional<Certificate> findByCertificateCode(String certificateCode);
}
