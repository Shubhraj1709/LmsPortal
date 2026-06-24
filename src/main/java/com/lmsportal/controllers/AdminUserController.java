package com.lmsportal.controllers;

import com.lmsportal.models.User;
import com.lmsportal.services.AdminUserService;
import com.lmsportal.dto.UserRoleDTO;
import com.lmsportal.dto.UserStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminUserController {


	private final AdminUserService adminUserService;


	public AdminUserController(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}


	@GetMapping("/students")
	public ResponseEntity<List<User>> getStudents() {
		return ResponseEntity.ok(adminUserService.getStudents());
	}


	@GetMapping("/instructors")
	public ResponseEntity<List<User>> getInstructors() {
		return ResponseEntity.ok(adminUserService.getInstructors());
	}


//	@PutMapping("/approve-instructor/{id}")
//	public ResponseEntity<User> approveInstructor(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(adminUserService.approveInstructor(id));
//	}


	@PutMapping("/status")
	public ResponseEntity<User> setStatus(@RequestBody UserStatusDTO dto) {
		return ResponseEntity.ok(adminUserService.setActive(dto.getUserId(), dto.isActive()));
	}
	
	@GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminUserService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.getUserById(id));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<User> updateUserRole(@PathVariable Long id,
                                               @RequestBody UserRoleDTO dto) {
        return ResponseEntity.ok(adminUserService.updateRole(id, dto.getRole()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return ResponseEntity.ok("User deleted.");
    }

    // Instructor Workflow
    @GetMapping("/instructors/pending")
    public ResponseEntity<List<User>> pendingInstructors() {
        return ResponseEntity.ok(adminUserService.getPendingInstructors());
    }

    @PutMapping("/instructors/approve/{id}")
    public ResponseEntity<User> approveInstructor(@PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.approveInstructor(id));
    }

//    @PutMapping("/instructors/reject/{id}")
//    public ResponseEntity<User> rejectInstructor(@PathVariable Long id) {
//        return ResponseEntity.ok(adminUserService.rejectInstructor(id));
//    }
    
    @DeleteMapping("/instructors/reject/{id}")
    public ResponseEntity<Void> rejectInstructor(@PathVariable Long id) {
        adminUserService.rejectInstructor(id);
        return ResponseEntity.noContent().build(); // 204
    }

}