package com.lmsportal.controllers;

import com.lmsportal.services.AdminReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin/reports")
public class AdminReportController {


	private final AdminReportService adminReportService;


	public AdminReportController(AdminReportService adminReportService) {
		this.adminReportService = adminReportService;
	}


	@GetMapping("/enrollments")
	public ResponseEntity<?> enrollments() {
		return ResponseEntity.ok(adminReportService.enrollmentReport());
	}


	@GetMapping("/revenue")
	public ResponseEntity<?> revenue() {
		return ResponseEntity.ok(adminReportService.revenueReport());
	}
	
}