package org.ooka.usermngmt.rest;

import org.ooka.usermngmt.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthResource.PATH)
public class AuthResource {
    static final String PATH = "/api/v1/auth";

    private JwtService jwtService;

    @Autowired
    public AuthResource(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Dummy rest endpoint for auth validation. The response header including the authorization header will be handled
    // by the security configuration (look WebSecurityConfig.java).
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity validate() {
        return ResponseEntity
                .status(200)
                .build();
    }
}