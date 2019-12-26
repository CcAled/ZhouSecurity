/**
 * 
 */
package com.zhou.security.core.properties;


/**
 * @author zhailiang
 *
 */
public class QQProperties extends SpringBootSocialProperties {
	
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}
