package com.meta.validation;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import javax.validation.Validator;

public class Validate {

    public <T>String validation(final T type, final Class<?> groupingClass) {
        final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory();
        final Validator validator = validatorFactory.getValidator();
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(type, groupingClass);

        if (0 < constraintViolations.size()) {

            for (final ConstraintViolation<T> violation : constraintViolations) {
                return  violation.getMessage();
            }
        }

        return null;
    }
}
