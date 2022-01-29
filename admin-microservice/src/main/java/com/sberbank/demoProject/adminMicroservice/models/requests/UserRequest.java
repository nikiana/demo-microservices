package com.sberbank.demoProject.adminMicroservice.models.requests;

import com.sberbank.demoProject.adminMicroservice.enums.RoleEnum;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Value
@Builder
@Jacksonized
public class UserRequest {
    @NotNull
    @NotEmpty(message = "Е-мейл юзера - обязательное поле")
    String email;
    @NotNull
    @NotEmpty(message = "Пароль юзера - обязательное поле")
    String password;
    String firstName;
    String lastName;
    @NotNull
    Set<RoleEnum> roleEnums;
}
