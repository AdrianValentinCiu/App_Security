package com.security.API_App.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> { // <ClassName, id type>
    Optional<User> findByEmail(String email);
}
