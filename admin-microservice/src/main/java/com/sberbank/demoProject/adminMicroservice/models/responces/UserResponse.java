package com.sberbank.demoProject.adminMicroservice.models.responces;

import com.sberbank.demoProject.adminMicroservice.enums.RoleEnum;
import lombok.Value;

import java.util.Set;

@Value
public class UserResponse {
    Long id;
    String email;
    String firstName;
    String lastName;
    Set<RoleEnum> roles;
}
