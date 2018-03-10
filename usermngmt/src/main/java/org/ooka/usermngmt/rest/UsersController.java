package org.ooka.usermngmt.rest;

import org.ooka.usermngmt.domain.User;
import org.ooka.usermngmt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UsersController.PATH)
public class UsersController {
    static final String PATH = "/api/v1/users";

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return ResponseEntity
                .status(200)
                .body(users);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = this.usersService.createUser(user);
        String userUrl = UsersController.PATH + "/" + savedUser.getId();
        return ResponseEntity
                .status(201)
                .header("Location", userUrl)
                .body(savedUser);
    }
}
