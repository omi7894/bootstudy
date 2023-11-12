package com.example.bootstudy.common.valid;

import com.example.bootstudy.common.annotation.ValidationAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ParameterValidator implements ConstraintValidator<ValidationAnnotation, String> {
    @Override
    public void initialize(ValidationAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.equals("hello");
    }
}