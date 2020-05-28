package com.example.socialapp.api.repository;
import com.example.socialapp.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);
}