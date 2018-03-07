package org.ooka.usermngmt.services;

import org.ooka.usermngmt.domain.CredentialsMessage;
import org.ooka.usermngmt.domain.User;
import org.ooka.usermngmt.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersProvider) {
        this.usersRepository = usersProvider;
    }

    public User generateUser(CredentialsMessage credentialsMessage) {
        User user = this.usersRepository.findByUserName(credentialsMessage.getEmail());
        if (user == null) {
            user = new User(credentialsMessage);
            this.usersRepository.save(user);
        }
        return user;
    }

    public List<User> getAllUsers() {
        return this.usersRepository.findAll();
    }
}
