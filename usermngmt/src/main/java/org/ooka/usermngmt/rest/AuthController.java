package org.ooka.usermngmt.rest;

import org.ooka.usermngmt.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.PATH)
public class AuthController {
    static final String PATH = "/api/v1/auth";

    @Autowired
    public AuthController() {
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