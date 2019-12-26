package com.zhou.code;

import com.zhou.security.core.validate.code.ImageCode;
import com.zhou.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * zhousecurity
 * 2019/11/5 14:06
 *
 * @author ChristChou
 * @since
 **/
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("自定义图形验证码");
        return null;
    }
}
