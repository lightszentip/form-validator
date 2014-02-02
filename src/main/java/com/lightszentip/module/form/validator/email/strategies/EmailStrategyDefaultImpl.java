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
package com.lightszentip.module.form.validator.email.strategies;

import java.util.regex.Matcher;

public class EmailStrategyDefaultImpl implements EmailStrategy {

	/** The pattern. */
	private java.util.regex.Pattern pattern = java.util.regex.Pattern
			.compile(
					"^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@[A-Za-z0-9äöüÄÖÜ-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
					java.util.regex.Pattern.CASE_INSENSITIVE);

	@Override
	public boolean isValid(final String value) {
		if (value == null || value.length() == 0) {
			return true;
		}

		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
