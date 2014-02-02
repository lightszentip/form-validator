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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lightszentip.module.form.validator.annotation.EmailAddressValid;
import com.lightszentip.module.form.validator.email.strategies.EmailStrategy;

/**
 * The Class EmailValidation.
 * 
 * @author tgafner
 */
public class EmailValidation implements ConstraintValidator<EmailAddressValid, String> {

	private Class<? extends EmailStrategy> emailValidStrategy;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	public void initialize(EmailAddressValid annotation) {
		emailValidStrategy = annotation.strategy();
	}

	/**
	 * Checks if is valid.
	 * 
	 * @param value
	 *            the value
	 * @return true, if is valid
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public boolean isValid(final String value) {
		boolean match = false;
		if(emailValidStrategy != null) {
			try {
				match= emailValidStrategy.newInstance().isValid(value);
			} catch (InstantiationException e1) {
				//
			} catch (IllegalAccessException e2) {
				//
			}
		}
		return match;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return isValid(value);
	}

}
