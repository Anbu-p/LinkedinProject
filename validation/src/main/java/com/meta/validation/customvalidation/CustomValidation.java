package com.meta.validation.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidation {

    String message() default "Please enter a valid isLike value (true or false)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

