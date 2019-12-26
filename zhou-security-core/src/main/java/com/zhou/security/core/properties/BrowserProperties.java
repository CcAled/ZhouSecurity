/**
 * 
 */
package com.zhou.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author zhailiang
 *
 */
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();

	private String signUpUrl = "/zhou-signUp.html";

//	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	private String loginPage = "/zhou-signIn.html";

	private String signOutUrl;

	private LoginResponseType loginType = LoginResponseType.JSON;

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getLoginPage() {
		return loginPage;
	}




	private int rememberMeSeconds = 3600;

//	public String getLoginPage() {
//		return loginPage;
//	}


	public String getSignOutUrl() {
		return signOutUrl;
	}

	public void setSignOutUrl(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}
	
}
