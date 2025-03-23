package com.ifsp.bugtracker.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    @GetMapping()
    String GetIssues() {
        return "Hello Wolrd";
    }

    @GetMapping("/{id}")
    String GetIssue(@PathVariable UUID id) {
        return String.format("Hello %s", id);
    }
}
