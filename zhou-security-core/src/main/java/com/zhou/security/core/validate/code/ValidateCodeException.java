package com.zhou.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * zhousecurity
 * 2019/11/5 10:22
 *
 * @author ChristChou
 * @since
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
