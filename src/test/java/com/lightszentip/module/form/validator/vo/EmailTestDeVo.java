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
package com.lightszentip.module.form.validator.vo;

import com.lightszentip.module.form.validator.annotation.EmailAddressValid;
import com.lightszentip.module.form.validator.email.strategies.EmailStrategyTldDeImpl;

public class EmailTestDeVo {

	@EmailAddressValid(strategy = EmailStrategyTldDeImpl.class)
	private String email;

	public EmailTestDeVo() {
	}

	public EmailTestDeVo(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
