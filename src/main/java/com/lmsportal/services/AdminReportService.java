package com.lmsportal.services;

import com.lmsportal.repositories.EnrollmentRepository;
import com.lmsportal.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
public class AdminReportService {


	private final EnrollmentRepository enrollmentRepository;
	private final CourseRepository courseRepository;


	public AdminReportService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
		this.enrollmentRepository = enrollmentRepository;
		this.courseRepository = courseRepository;
	}


	public Map<String, Object> enrollmentReport() {
		long total = enrollmentRepository.count();
		return Map.of("totalEnrollments", total);
	}


	public Map<String, Object> revenueReport() {
		Double total = enrollmentRepository.findAll().stream().mapToDouble(e -> e.getAmountPaid() == null ? 0.0 : e.getAmountPaid()).sum();
		return Map.of("totalRevenue", total);
	}
	
}
