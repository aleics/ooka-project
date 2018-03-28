package org.ooka.usermngmt.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.ooka.usermngmt.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtProvider {
    private String secretKey;

    public JwtProvider() {
        String envSecretKey = System.getenv("jwt-secret");
        this.secretKey = envSecretKey != null ? envSecretKey : "ThisIsATemporalSecretKey";
    }

    public String createUserJwt(User user) throws Exception {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + TimeUnit.MINUTES.toMillis(30));

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        Claims claims = Jwts.claims()
                .setSubject(user.getEmail())
                .setExpiration(expirationTime);
        claims.put("user", userJson);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            System.out.println("ERROR: Token is not valid. Exception: " + e.toString());
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        if (token != null) {
            try {
                // parse the token.
                String user = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token.replace("Bearer", ""))
                        .getBody()
                        .getSubject();

                return user != null ?
                        new UsernamePasswordAuthenticationToken(user, null, Collections.EMPTY_LIST) :
                        null;
            } catch(Exception e) {
                System.out.println("ERROR: Couldn't get the authentication. Exception: " + e);
            }
        }
        return null;
    }
}
