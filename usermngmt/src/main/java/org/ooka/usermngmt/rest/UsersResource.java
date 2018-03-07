package org.ooka.usermngmt.rest;

import org.ooka.usermngmt.domain.User;
import org.ooka.usermngmt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UsersResource.PATH)
public class UsersResource {
    static final String PATH = "/api/v1/users";

    private UsersService usersService;

    @Autowired
    public UsersResource(UsersService usersService) {
        this.usersService = usersService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return ResponseEntity
                .status(200)
                .body(users);
    }
}
