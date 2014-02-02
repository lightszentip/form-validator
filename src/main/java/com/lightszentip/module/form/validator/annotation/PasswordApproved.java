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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lightszentip.module.form.validator.password.strategies.PasswordStrategy;
import com.lightszentip.module.form.validator.password.strategies.PasswordStrategyDefaultImpl;
import com.lightszentip.module.validator.validation.PasswordValidator;

/**
 * The Annotation PasswordApproved.
 * 
 * Validation annotation to validate an password value.
 * 
 * Example:
 * 
 * @PasswordApproved()
 * 
 * Example with strategy:
 * 
 * @PasswordApproved(strategy=PasswordStrategyDefaultImpl.class)
 * @PasswordApproved(strategy=PasswordStrategyDefaultImpl.class, message = "The password is not valid")
 * 
 * @since 1.0.0
 * @author tgafner
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
public @interface PasswordApproved {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "{message.validation.constraints.passwordapproved}";
	
	Class<? extends PasswordStrategy> strategy() default PasswordStrategyDefaultImpl.class;

	/**
	 * Groups.
	 *
	 * @return the class[]
	 */
	Class<?>[] groups() default { };

	/**
	 * Payload.
	 *
	 * @return the class<? extends payload>[]
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several <code>@EmailValid</code> annotations on the same element.
	 *
	 * @see com.lightszentip.module.form.validator.annotation.PasswordValid
	 * @author Emmanuel Bernard
	 */
	@Target({ FIELD, ANNOTATION_TYPE, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		
		/**
		 * Value.
		 *
		 * @return the email valid[]
		 */
	    PasswordApproved[] value();
	}
}
