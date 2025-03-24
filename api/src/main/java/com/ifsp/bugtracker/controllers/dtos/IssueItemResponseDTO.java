package com.ifsp.bugtracker.controllers.dtos;

import java.time.LocalDateTime;

import com.ifsp.bugtracker.data.enums.IssueStatus;

public record IssueItemResponseDTO(
    String title,
    String description,
    LocalDateTime date,
    String code,
    UserItemResponseDTO owner,
    IssueStatus status
) {

}
