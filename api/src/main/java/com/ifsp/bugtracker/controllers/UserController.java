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
import com.ifsp.bugtracker.services.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifsp.bugtracker.controllers.dtos.CreateUserRequestDTO;
import com.ifsp.bugtracker.controllers.dtos.PaginationResponseDTO;
import com.ifsp.bugtracker.controllers.dtos.UserItemResponseDTO;
import com.ifsp.bugtracker.data.entities.User;
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
    public ResponseEntity<PaginationResponseDTO<UserItemResponseDTO>> GetAllUsers(
        @RequestParam(defaultValue = "0") @Min(0) Integer page,
        @RequestParam(defaultValue = "5") @Min(1) @Max(10) Integer size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageRequest);
        PaginationResponseDTO<UserItemResponseDTO> response = PaginationResponseDTO.map(
            userPage,
            user -> new UserItemResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPictureUrl()
            )
        );
        return ResponseEntity.ok(response);
    }
}
