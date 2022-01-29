package com.sberbank.demoProject.adminMicroservice.validators;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Создаем constraint-аннотацию, которая валидируется EmailExistsConstraintValidator
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=EmailExistsConstraintValidator.class)
public @interface EmailExistsConstraint {
    String message() default "Пользователь с таким е-мейлом уже зарегистрирован";
    Class[] groups() default {};
    Class[] payload() default {};
}
