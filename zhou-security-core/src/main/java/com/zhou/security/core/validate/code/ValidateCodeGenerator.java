/**
 * 
 */
package com.zhou.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Chris Chou
 *
 */
public interface ValidateCodeGenerator {

//	ValidateCode generate(ServletWebRequest request);
	ValidateCode generate(ServletWebRequest request);

}
