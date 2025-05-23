package com.example.QuanLyDuLich.Validatior;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {PassValidator.class}
)
public @interface PassConstraint {
    String message() default "Password khong hop le";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
