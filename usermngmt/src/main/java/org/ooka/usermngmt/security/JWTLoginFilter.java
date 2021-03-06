package org.ooka.usermngmt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ooka.usermngmt.domain.CredentialsMessage;
import org.ooka.usermngmt.domain.User;
import org.ooka.usermngmt.services.JwtService;
import org.ooka.usermngmt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
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

    UsersService usersService;

    JwtService jwtService = new JwtService();

    public JWTLoginFilter(String url, AuthenticationManager authManager, UsersService usersService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.usersService = usersService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        String origin = req.getHeader(HttpHeaders.ORIGIN);
        if (origin != null && !origin.isEmpty()) {
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
            res.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        }

        if (req.getMethod().equals("OPTIONS")) {
            PrintWriter printWriter = res.getWriter();
            printWriter.write("OK");
            printWriter.flush();
            return null;
        }
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
        User user = this.usersService.getUserByEmail(auth.getName());
        String token = jwtService.createUserJwt(user);

        res.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + " " + token);
    }
}
