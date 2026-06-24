package com.lmsportal.dto;

import com.lmsportal.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private RoleEnum role;
    private Boolean active;
    private Boolean approved;
    

}
