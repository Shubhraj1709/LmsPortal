package com.lmsportal.dto;

import com.lmsportal.enums.RoleEnum;
import lombok.Data;

@Data
public class UserRoleDTO {
    private Long userId;
    private RoleEnum role;
}
