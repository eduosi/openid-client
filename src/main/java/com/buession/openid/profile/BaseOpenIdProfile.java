/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with the License. You may obtain 
 * a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * =================================================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * +------------------------------------------------------------------------------------------------+
 * | License: http://openid-client.buession.com.cn/LICENSE 											|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2014 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.openid.profile;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.openid.profile.OpenIdProfile;

import com.buession.open.profile.Profile;

/**
 * @author Yong.Teng <webmaster@buession.com>
 */
public abstract class BaseOpenIdProfile extends OpenIdProfile implements Profile {

	private static final long serialVersionUID = 7769220276513752800L;

	public String getRealName() {
		return null;
	}

	@Override
	public String getDisplayName() {
		String displayName = (String) getAttribute("display_name");

		if (displayName == null || displayName.length() == 0) {
			String realName = getRealName();

			if (realName == null || realName.length() == 0) {
				String firstName = getFirstName();
				if (firstName == null) {
					return getUsername();
				} else {
					String familyName = getFamilyName();
					return familyName == null ? firstName : firstName + " " + familyName;
				}
			} else {
				return realName;
			}
		} else {
			return displayName;
		}
	}

	public String getAvatar() {
		return null;
	}

	@Override
	public String getPictureUrl() {
		return getAvatar();
	}

	public String getCountry() {
		return null;
	}

	public String getProvince() {
		return null;
	}

	public String getCity() {
		return null;
	}

	public String getAddress() {
		return null;
	}

	@Override
	public String getLocation() {
		return getAddress();
	}

	public boolean getIsVip() {
		return false;
	}

	/**
	 * 返回用户资料属性属性定义
	 * 
	 * @return 用户资料属性属性定义
	 */
	@Override
	protected AttributesDefinition getAttributesDefinition() {
		return null;
	}

}
