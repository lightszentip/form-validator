/**
 * Copyright  cc (C) 2013 Tobias Gafner <lightszentip@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lightszentip.module.validator.validation;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lightszentip.module.form.validator.annotation.PasswordNotEqual;

/**
 * The Class FieldMatchValidator.
 */
public class PasswordEqualCheckValidator implements ConstraintValidator<PasswordNotEqual, Object> {

    /** The passwordField field name. */
    private String passwordField;

    /** The passwordRepeatField field name. */
    private String passwordRepeatField;

    /** The message value */
    private String message;

    /**
	 * 
	 */
    @Override
    public void initialize(final PasswordNotEqual constraintAnnotation) {
        passwordField = constraintAnnotation.passwordField();
        passwordRepeatField = constraintAnnotation.passwordConfirmField();
        message = constraintAnnotation.message();
    }

    /**
	 * 
	 */
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean match = false;
        try {
            Field firstField = value.getClass().getDeclaredField(passwordField);
            firstField.setAccessible(true);
            final String firstObj = (String) firstField.get(value);
            Field secondField = value.getClass().getDeclaredField(passwordRepeatField);
            secondField.setAccessible(true);
            final String secondObj = (String) secondField.get(value);

            if (passwordsAreEqual(firstObj,secondObj)) {
                match = true;
            }
        } catch (final Exception ex) {
            throw new RuntimeException("Exception occurred during validation", ex);
        }

        if (!match) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addNode(passwordField).addNode(passwordRepeatField).addConstraintViolation();
        }

        return match;
    }
    
    private boolean passwordsAreEqual(String password, String password2) {
        return (password == null ?  password2 == null : password.equals(password2));
    }
}
