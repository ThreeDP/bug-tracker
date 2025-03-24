package com.ifsp.bugtracker.controllers.dtos;

import java.util.UUID;

public record CreateIssueRequestDTO(
    String title,
    String description,
    UUID userId
) {

}
