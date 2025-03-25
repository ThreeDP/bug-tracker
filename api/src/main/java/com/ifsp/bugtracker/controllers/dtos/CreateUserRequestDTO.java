package com.ifsp.bugtracker.controllers.dtos;

import com.ifsp.bugtracker.data.enums.UserRole;

public record CreateUserRequestDTO(String name, String email, String pass, UserRole role) {

}
