package edu.java.web.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by edwin on 20/01/17.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("CustomAccessDeniedHandler : go to login page");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/loginpage?error=access-denied");
    }
}
