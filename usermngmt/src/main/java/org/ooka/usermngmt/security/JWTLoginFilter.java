package org.ooka.usermngmt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ooka.usermngmt.domain.CredentialsMessage;
import org.ooka.usermngmt.services.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    static final String TOKEN_PREFIX = "Bearer";

    JwtService jwtService = new JwtService();

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        CredentialsMessage creds = new ObjectMapper()
                .readValue(req.getInputStream(), CredentialsMessage.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getEmail(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) {
        String token = jwtService.createUserJwt(auth.getName());

        res.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + " " + token);

        try {
            PrintWriter printWriter = res.getWriter();
            printWriter.write(auth.getAuthorities().toString());
            printWriter.flush();
        } catch (Exception e) {
            System.out.println("ERROR: Couldn't set the body in the login request. Exception: " + e.getMessage());
        }
    }
}
