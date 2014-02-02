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
package com.lightszentip.module.form.validator.password.strategies;

import java.util.regex.Matcher;

public class PasswordStrategyBsiImpl implements PasswordStrategy {

	/** The pattern. */
	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,}");
	
	@Override
	public boolean isValid(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}

		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
