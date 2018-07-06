package com.engcorner.epose.repository.user;

import com.engcorner.epose.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByName(String name);
}
