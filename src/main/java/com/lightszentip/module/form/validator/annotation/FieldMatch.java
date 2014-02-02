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
package com.lightszentip.module.form.validator.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lightszentip.module.validator.validation.FieldMatchValidator;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 * 
 * Example, compare 1 pair of fields:
 * 
 * @FieldMatch(first = "password", second = "confirmPassword", message =
 *                   "The password fields must match")
 * 
 *                   Example, compare more than 1 pair of fields:
 * @FieldMatch.List({
 * @FieldMatch(first = "password", second = "confirmPassword", message =
 *                   "The password fields must match"),
 * @FieldMatch(first = "email", second = "confirmEmail", message =
 *                   "The email fields must match")})
 *                   
 * @since 1.0.0
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
	String message() default "{message.validation.constraints.fieldmatch}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return The first field
	 */
	String first();

	/**
	 * @Retention(RUNTIME)
	 * @Documented
	 * @interface List { FieldMatch[] value(); }
	 * @return The second field
	 */
	String second();

	/**
	 * Defines several <code>@FieldMatch</code> annotations on the same element
	 * 
	 * @see FieldMatch
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		FieldMatch[] value();
	}
}
