package com.lmsportal.dto;

import com.lmsportal.models.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
private String token;
private String tokenType = "Bearer";
private String name;
private String role;

private UserDto user;  // send full user info

public JwtResponse(String token, User user) {
    this.token = token;
    this.name = user.getName();
    this.role = user.getRole().name();
    this.user = new UserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
//            user.isActive(),
//            user.isApproved()
            user.getActive(),     // UPDATED
            user.getApproved() 
    );
}
}
