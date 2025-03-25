package com.ifsp.bugtracker.controllers.dtos;

public record AuthUserRequestDTO(
    String email,
    String password
) {

}
