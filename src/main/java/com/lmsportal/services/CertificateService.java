package com.lmsportal.services;

import com.lmsportal.models.Certificate;

public interface CertificateService {

    Certificate generateCertificate(Long studentId, Long courseId);

    Certificate getCertificate(Long studentId, Long courseId);

    byte[] downloadCertificate(Long studentId, Long courseId);
}
