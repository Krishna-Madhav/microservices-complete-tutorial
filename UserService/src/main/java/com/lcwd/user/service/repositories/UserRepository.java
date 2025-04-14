package com.emission.lcwd.user.service.repositories;

import com.emission.lcwd.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    // We can even write any custom method or query

}


