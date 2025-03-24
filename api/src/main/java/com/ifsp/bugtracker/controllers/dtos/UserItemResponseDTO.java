package com.ifsp.bugtracker.controllers.dtos;

public record UserItemResponseDTO(
    String name,
    String pictureUrl,
    String email) {
}
