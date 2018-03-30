package com.senacor.code.fullstack.chat.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        String method = httpRequest.getMethod();
        if (!method.equals(HttpMethod.OPTIONS.toString())) {
            if (token != null && !token.isEmpty()) {
                AuthService authService = new AuthService();
                HttpStatus status = authService.authenticate(token);
                if (!status.equals(HttpStatus.OK)) {
                    abortRequest(response, status);
                }
            } else {
                abortRequest(response, HttpStatus.FORBIDDEN);
            }
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        chain.doFilter(request, response);
    }

    private void abortRequest(ServletResponse response, HttpStatus status) {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.setStatus(status.value());
    }
}

