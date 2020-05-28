package com.example.socialapp.api.service;

import com.example.socialapp.api.exception.UserServiceException;
import com.example.socialapp.api.models.Role;
import com.example.socialapp.api.repository.UserRepository;
import com.example.socialapp.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> saveUser (User user) throws UserServiceException {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        Optional<User> optionalUserByEmail = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isEmpty() && optionalUserByEmail.isEmpty()){
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setUsername(user.getUsername());
            //user.setDateOfBirth(user.getDateOfBirth().plusDays(1));
//            user.setRole(Role.MEMBER);
            //user.setRole("ROLE_USER");
            user.setCreatedAt(LocalDateTime.now());
            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }else{
            if(optionalUser.isPresent() && optionalUserByEmail.isPresent()){
                throw new UserServiceException("Username and Email is already in use!");
            }
            else if(optionalUser.isPresent()){
                throw new UserServiceException("Username is already in use!");
            }
            else{
                throw new UserServiceException("Email is already in use!");
            }
        }
        return ResponseEntity.ok().build();

    }



    public User findById(long id) { return userRepository.findById(id).get();}

}
