package com.ifsp.bugtracker.controllers.dtos;

import java.util.UUID;

public record UserItemResponseDTO(
    UUID id,
    String name,
    String pictureUrl,
    String email) {
}
