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
/**
 * 
 */
package com.lightszentip.module.form.validator.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lightszentip.module.validator.validation.PasswordEqualCheckValidator;

/**
 * Validation annotation to validate two password Fields.
 * 
 * Example, compare 1 pair of fields:
 * 
 * @PasswordNotEqual(passwordField = "password", passwordRepeatField = "confirmPassword")
 *                   
 * @since 1.1.0
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualCheckValidator.class)
@Documented
public @interface PasswordNotEqual {
	
	String message() default "message.validation.constraints.passwordnotequal";
	
	String passwordField();
	
	String passwordConfirmField();
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	/**
	 * Defines several <code>@PasswordNotEqual</code> annotations on the same element
	 * 
	 * @see FieldMatch
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
	    PasswordNotEqual[] value();
	}

}
