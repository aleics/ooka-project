package org.ooka.usermngmt.services;

import org.ooka.usermngmt.domain.User;
import org.ooka.usermngmt.domain.UserAuth;
import org.ooka.usermngmt.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersProvider) {
        this.usersRepository = usersProvider;
    }

    public List<User> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public User createUser(User user) {
        user.setPassword(user.getPassword());
        return this.usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        } else {
            return new UserAuth(user);
        }
    }
}
