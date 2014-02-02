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

import com.lightszentip.module.form.validator.vo.PasswordTestBsiVo;
import com.lightszentip.module.form.validator.vo.PasswordTestExceptionVo;
import com.lightszentip.module.form.validator.vo.PasswordTestVo;

public class PasswordApprovedTest {

	private static LocalValidatorFactoryBean localValidatorFactory;

	@BeforeClass
	public static void setUp() {
		localValidatorFactory = new LocalValidatorFactoryBean();
		localValidatorFactory.setProviderClass(HibernateValidator.class);
		localValidatorFactory.afterPropertiesSet();
	}

	@Test
	public void passwordIsValid() {

		PasswordTestVo password = new PasswordTestVo("abcdef");

		Set<ConstraintViolation<PasswordTestVo>> constraintViolations = localValidatorFactory
				.validate(password);
		assertEquals(0, constraintViolations.size());

	}

	@Test
	public void passwordIsNotValid() {

		PasswordTestVo password = new PasswordTestVo("a");

		Set<ConstraintViolation<PasswordTestVo>> constraintViolations = localValidatorFactory
				.validate(password);

		assertEquals(1, constraintViolations.size());
		assertEquals("{message.validation.constraints.passwordapproved}",
				constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void passwordIsValidBsi() {

		PasswordTestBsiVo password = new PasswordTestBsiVo("abcdEFfg1$");

		Set<ConstraintViolation<PasswordTestBsiVo>> constraintViolations = localValidatorFactory
				.validate(password);
		assertEquals(0, constraintViolations.size());

	}

	@Test
	public void passwordIsNotValidBsi() {

		PasswordTestBsiVo password = new PasswordTestBsiVo("abcdEFfgh");

		Set<ConstraintViolation<PasswordTestBsiVo>> constraintViolations = localValidatorFactory
				.validate(password);

		assertEquals(1, constraintViolations.size());
		assertEquals("{message.validation.constraints.passwordapproved}",
				constraintViolations.iterator().next().getMessage());
	}
	
	@Test(expected=RuntimeException.class)
	public void passwordValidException() {

		PasswordTestExceptionVo password = new PasswordTestExceptionVo("a");

		Set<ConstraintViolation<PasswordTestExceptionVo>> constraintViolations = localValidatorFactory
				.validate(password);

		assertEquals(1, constraintViolations.size());
		assertEquals("{message.validation.constraints.passwordapproved}",
				constraintViolations.iterator().next().getMessage());
	}

}
