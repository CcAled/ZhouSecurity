/**
 * 
 */
package com.zhou.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
@ConfigurationProperties(prefix = "zhou.security")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();

	private ValidateCodeProperties code = new ValidateCodeProperties();

	private SocialProperties social = new SocialProperties();

	private OAuth2Properties oAuth2 = new OAuth2Properties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}

	public OAuth2Properties getoAuth2() {
		return oAuth2;
	}

	public void setoAuth2(OAuth2Properties oAuth2) {
		this.oAuth2 = oAuth2;
	}
}

