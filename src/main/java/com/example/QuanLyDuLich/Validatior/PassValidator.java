package com.example.QuanLyDuLich.Validatior;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PassValidator implements ConstraintValidator<PassConstraint,String> {
    private static final String PASS= "^(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
    @Override
    public void initialize(PassConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return s!=null&&s.matches(PASS);
    }

}
