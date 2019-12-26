package com.zhou.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhou.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * zhousecurity
 * 2019/11/8 13:59
 *
 * @author ChristChou
 * @since
 **/
public class ZhouLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public ZhouLogoutSuccessHandler(String signOutUrl){
        this.signOutUrl = signOutUrl;
    }

//    @Autowired
    private String signOutUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");


        if(StringUtils.isBlank(signOutUrl)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        }else{
            response.sendRedirect(signOutUrl);
        }

    }
}
