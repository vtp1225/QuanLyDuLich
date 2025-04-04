package com.example.QuanLyDuLich.Validatior;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDateTime> {
    private int min;

    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        var year = ChronoUnit.YEARS.between(localDateTime,LocalDateTime.now());

        return year>=min;
    }
}
