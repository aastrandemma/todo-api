package org.github.aastrandemma.todoapi.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.github.aastrandemma.todoapi.exception.DataNotFoundException;

import java.util.Set;

public class ValidationUtil {
    public static <T> void validateObject(T object, Validator validator, String message) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new DataNotFoundException(message);
        }
    }
}