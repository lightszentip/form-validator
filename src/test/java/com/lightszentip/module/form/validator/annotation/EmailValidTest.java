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

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.HibernateValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.lightszentip.module.form.validator.vo.EmailTestDeVo;
import com.lightszentip.module.form.validator.vo.EmailTestVo;

public class EmailValidTest {

	private static LocalValidatorFactoryBean localValidatorFactory;

	@BeforeClass
	public static void setUp() {
		localValidatorFactory = new LocalValidatorFactoryBean();
		localValidatorFactory.setProviderClass(HibernateValidator.class);
		localValidatorFactory.afterPropertiesSet();
	}

	@Test
	public void emailAddressIsValid() {

		EmailTestVo email = new EmailTestVo("xyz@xyz.de");

		Set<ConstraintViolation<EmailTestVo>> constraintViolations = localValidatorFactory
				.validate(email);
		assertEquals(0, constraintViolations.size());

	}

	@Test
	public void emailAddressIsNotValid() {

		EmailTestVo email = new EmailTestVo("xyz@de");

		Set<ConstraintViolation<EmailTestVo>> constraintViolations = localValidatorFactory
				.validate(email);

		assertEquals(1, constraintViolations.size());
		assertEquals("{message.validation.constraints.email}",
				constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void emailAddressIsValidUmlaute() {

		EmailTestVo email = new EmailTestVo("test@wäscherei.de");

		Set<ConstraintViolation<EmailTestVo>> constraintViolations = localValidatorFactory
				.validate(email);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void emailAddressIsValidSubdomains() {

		EmailTestVo email = new EmailTestVo("test@com.gnetos.de");

		Set<ConstraintViolation<EmailTestVo>> constraintViolations = localValidatorFactory
				.validate(email);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void emailAddressIsValidMinus() {

		EmailTestVo email = new EmailTestVo("test@o-u.de");

		Set<ConstraintViolation<EmailTestVo>> constraintViolations = localValidatorFactory
				.validate(email);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void emailAddressDeIsNotValid() {

		EmailTestDeVo email = new EmailTestDeVo("xyz@xyz.com");

		Set<ConstraintViolation<EmailTestDeVo>> constraintViolations = localValidatorFactory
				.validate(email);

		assertEquals(1, constraintViolations.size());
		assertEquals("{message.validation.constraints.email}",
				constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void emailAddressDeIsValid() {

		EmailTestDeVo email = new EmailTestDeVo("xyz@xyz.de");

		Set<ConstraintViolation<EmailTestDeVo>> constraintViolations = localValidatorFactory
				.validate(email);

		assertEquals(0, constraintViolations.size());
	}

}
