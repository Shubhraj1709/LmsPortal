package com.lmsportal.services;

import com.lmsportal.repositories.NotificationRepository;
import com.lmsportal.repositories.UserRepository;
import com.lmsportal.dto.NotificationDTO;
import com.lmsportal.enums.RoleEnum;
import com.lmsportal.models.Notification;
import com.lmsportal.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class AdminUserService {

    private final NotificationRepository repo;

	private final UserRepository userRepository;


	public AdminUserService(UserRepository userRepository, NotificationRepository repo) {
			this.userRepository = userRepository;
			this.repo = repo;
	}


	public List<User> getStudents() {
			return userRepository.findAll().stream().filter(u -> u.getRole() == com.lmsportal.enums.RoleEnum.STUDENT).toList();
	}


	public List<User> getInstructors() {
			return userRepository.findAll().stream().filter(u -> u.getRole() == com.lmsportal.enums.RoleEnum.INSTRUCTOR).toList();
	}


//	@Transactional
//	public User approveInstructor(Long userId) {
//		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//		user.setApproved(true);
//		return userRepository.save(user);
//	}


	@Transactional
	public User setActive(Long userId, boolean active) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		user.setActive(active);
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateRole(Long id, RoleEnum role) {
        User u = getUserById(id);
        u.setRole(role);
        return userRepository.save(u);
    }

    public void deleteUser(Long id) { userRepository.deleteById(id); }

    // Instructor workflow
    public List<User> getPendingInstructors() {
        return userRepository.findByRoleAndApproved(RoleEnum.INSTRUCTOR, false);
    }

    public User approveInstructor(Long id) {
        User u = getUserById(id);
        u.setApproved(true);
        return userRepository.save(u);
    }

//    public User rejectInstructor(Long id) {
//        User u = getUserById(id);
//        u.setApproved(false);
//        return userRepository.save(u);
//    }
    public void rejectInstructor(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Optional safety check
        if (user.getRole() != RoleEnum.INSTRUCTOR) {
            throw new RuntimeException("User is not an instructor");
        }

        userRepository.deleteById(id);
    }

	
   
}
