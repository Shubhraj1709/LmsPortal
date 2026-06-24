package com.lmsportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SignupRequest {
@NotBlank
private String name;


@Email
@NotBlank
private String email;


@NotBlank
@Pattern(
        regexp = "^(?=(?:.*\\d){2,})(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must be at least 8 characters, include 1 uppercase, 2 digits, and 1 special character"
    )
private String password;


// optional: allow client to request role; default will be STUDENT if null
private String role;
}