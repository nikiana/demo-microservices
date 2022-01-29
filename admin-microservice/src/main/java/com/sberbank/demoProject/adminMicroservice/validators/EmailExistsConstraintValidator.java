package com.sberbank.demoProject.adminMicroservice.validators;

import com.sberbank.demoProject.adminMicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор проверяет, существует ли уже пользователь с таким е-мейлом в БД
 * Если да - валидация не пройдена, создать юзера с таким же е-мейлом невозможно
 */
@RequiredArgsConstructor
public class EmailExistsConstraintValidator implements ConstraintValidator<EmailExistsConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailExistsConstraint emailExistsConstraint) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
