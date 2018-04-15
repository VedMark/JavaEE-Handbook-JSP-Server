package com.javaeehandbook.authorization;

import com.javaeehandbook.ApplicationConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/addTechnology", "/deleteTechnology", "/editTechnology" })
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final LoginState loginState = LoginState.getLoginState(request);

        switch (loginState) {
            case NOT_LOGGED_IN:
                request.getSession(false).setAttribute("errorMessage", "Authorization Required!");
                response.sendRedirect(request.getContextPath() + ApplicationConstants.PATH_HOME);
                break;
            case LOGGED_IN:
                filterChain.doFilter(servletRequest, servletResponse);
                break;
        }
    }

    public void destroy() {

    }
}
