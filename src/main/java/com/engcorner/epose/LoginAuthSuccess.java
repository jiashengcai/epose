package com.engcorner.epose;

import com.engcorner.epose.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthSuccess extends SimpleUrlAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException,
            ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        if (authentication.getPrincipal() instanceof User) {
        	WebAuthenticationDetails wauth = (WebAuthenticationDetails) authentication.getDetails();
            User user = (User) authentication.getPrincipal();
            // 登录成功后存储用户信息
        }
    }
}