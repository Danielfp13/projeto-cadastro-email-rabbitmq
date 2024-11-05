package com.github.Danielfp13.emailsender.service.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCpfValidation.class)
public @interface UniqueCpf {
    String message() default "There is already a user with this cpf.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
