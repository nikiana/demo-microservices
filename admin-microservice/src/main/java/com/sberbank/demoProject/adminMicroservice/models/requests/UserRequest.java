package com.sberbank.demoProject.adminMicroservice.models.requests;

import com.sberbank.demoProject.adminMicroservice.enums.RoleEnum;
import com.sberbank.demoProject.adminMicroservice.validators.EmailExistsConstraint;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Value
public class UserRequest {
    @NotBlank(message = "Е-мейл пользователя - обязательное поле")
    /**
     * Наша кастомная аннотация. См. реализацию в папке validators
     */
    @EmailExistsConstraint
    String email;
    @NotBlank(message = "Пароль пользователя - обязательное поле")
    String password;
    String firstName;
    String lastName;
    @NotEmpty(message = "Список ролей пользователя не может быть пустым")
    Set<RoleEnum> roleEnums;
}
