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
package com.buession.openid.client;

import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.openid.client.BaseOpenIdClient;
import org.pac4j.openid.credentials.OpenIdCredentials;

import com.buession.openid.profile.OpenIdAttributesDefinitions;
import com.buession.openid.profile.alipay.AlipayProfile;

/**
 * @author Yong.Teng <webmaster@buession.com>
 */
public class AlipayClient extends BaseOpenIdClient<AlipayProfile> {

	@Override
	public String toString() {
		return CommonHelper.toString(getClass(), "callbackUrl", callbackUrl, "name", getName());
	}

	@Override
	protected String getUser(WebContext context) {
		return "https://mapi.alipay.com/gateway.do";
	}

	@Override
	protected FetchRequest getFetchRequest() throws MessageException {
		return null;
	}

	@Override
	protected AlipayProfile createProfile(AuthSuccess authSuccess) throws MessageException {
		final AlipayProfile profile = new AlipayProfile();

		if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
			final FetchResponse fetchResp = (FetchResponse) authSuccess
					.getExtension(AxMessage.OPENID_NS_AX);
			for (final String name : OpenIdAttributesDefinitions.alipayDefinition
					.getAllAttributes()) {
				profile.addAttribute(name, fetchResp.getAttributeValue(name));
			}
		}

		return profile;
	}

	@Override
	protected BaseClient<OpenIdCredentials, AlipayProfile> newClient() {
		return new AlipayClient();
	}

	@Override
	protected boolean isDirectRedirection() {
		return false;
	}

}