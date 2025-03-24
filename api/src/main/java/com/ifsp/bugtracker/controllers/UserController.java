package com.ifsp.bugtracker.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifsp.bugtracker.repositories.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifsp.bugtracker.controllers.dtos.CreateUserRequestDTO;
import com.ifsp.bugtracker.data.entities.User;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserInfoById(@PathVariable UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user.get());
    }

    @GetMapping()
    public ResponseEntity<Page<User>> getMethodName(
        @RequestParam(defaultValue = "0") @Min(0) Integer page,
        @RequestParam(defaultValue = "5") @Min(1) @Max(10) Integer size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(userRepository.findAll(pageRequest));
    }

    @PostMapping("/:create")
    public ResponseEntity<Void> postMethodName(@RequestBody CreateUserRequestDTO request) {
        User user = new User(request.name(), request.email());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
