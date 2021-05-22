package com.springcourse.jwd.hardziyevich.app.repos;

import com.springcourse.jwd.hardziyevich.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
