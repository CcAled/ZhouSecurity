/**
 * 
 */
package com.zhou.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author zhailiang
 *
 */
public class ValidateCode implements Serializable {

	private BufferedImage image;

	private String code;

	private LocalDateTime expiretime;

	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expiretime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expiretime = expireTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(LocalDateTime expiretime) {
		this.expiretime = expiretime;
	}

    public boolean isExpried() {
		return LocalDateTime.now().isAfter(expiretime);
    }
}
