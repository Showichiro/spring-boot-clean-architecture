package com.example.cleanarchitecture.infrastructure.product.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueName {
    String message() default "product name is already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
