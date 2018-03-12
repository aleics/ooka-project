package org.ooka.usermngmt.services;

import org.ooka.usermngmt.providers.JwtProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    JwtProvider jwtProvider;

    public JwtService() {
        jwtProvider = new JwtProvider();
    }

    public String createUserJwt(String userName) {
        return jwtProvider.createUserJwt(userName);
    }

    public boolean validate(String token) {
        return jwtProvider.validate(token);
    }

    public Authentication getAuthentication(String token) {
        return jwtProvider.getAuthentication(token);
    }
}
