package com.ifsp.bugtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.ifsp.bugtracker.data.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ifsp.bugtracker.controllers.dtos.CreateUserRequestDTO;
import com.ifsp.bugtracker.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = repository.findByEmail(username);
        return user;
    }

    public UserDetails signUp(CreateUserRequestDTO data) throws JWTVerificationException {
        if (repository.findByEmail(data.email()) != null) {
            throw new JWTVerificationException("Username already exists");
        }
        String hashPass = new BCryptPasswordEncoder().encode(data.pass());
        User newUser = new User(data.name(), data.email(), hashPass, data.role());
        return repository.save(newUser);
    }
}
