package org.ooka.usermngmt.repositories;

import org.ooka.usermngmt.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
}
