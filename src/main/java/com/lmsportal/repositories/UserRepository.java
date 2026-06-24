package com.lmsportal.repositories;

import com.lmsportal.enums.RoleEnum;
import com.lmsportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail(String email);
boolean existsByEmail(String email);

List<User> findByRoleAndApproved(RoleEnum role, boolean approved);

List<User> findByRole(RoleEnum role);


}
