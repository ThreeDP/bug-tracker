package com.ifsp.bugtracker.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.ifsp.bugtracker.controllers.dtos.CreateIssueRequestDTO;
import com.ifsp.bugtracker.controllers.dtos.IssueItemResponseDTO;
import com.ifsp.bugtracker.controllers.dtos.PaginationResponseDTO;
import com.ifsp.bugtracker.controllers.dtos.UserItemResponseDTO;
import com.ifsp.bugtracker.data.entities.Issue;
import com.ifsp.bugtracker.data.entities.User;
import com.ifsp.bugtracker.repositories.IssueRepository;
import com.ifsp.bugtracker.repositories.UserRepository;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private IssueRepository issueRepository;
    private UserRepository userRepository;

    @Autowired
    public IssueController(IssueRepository issueRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<PaginationResponseDTO<IssueItemResponseDTO>> GetIssues(
        @RequestParam(defaultValue = "0") @Min(0) Integer page,
        @RequestParam(defaultValue = "5") @Min(1) @Max(10) Integer size
    ) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Issue> issuePage = issueRepository.findAll(pageRequest);
        PaginationResponseDTO<IssueItemResponseDTO> response = PaginationResponseDTO.map(
            issuePage,
            issue -> new IssueItemResponseDTO(
                issue.getTitle(),
                issue.getDescription(),
                issue.getCreatedAt(),
                issue.getId().toString(),
                new UserItemResponseDTO(
                    issue.getUser().getId(),
                    issue.getUser().getName(),
                    issue.getUser().getPictureUrl(),
                    issue.getUser().getEmail()),
                issue.getIssueStatus()
            )
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> GetIssue(@PathVariable UUID id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(issue.get());
    }

    @PostMapping("/:create")
    public ResponseEntity<String> CreateIssue(@RequestBody CreateIssueRequestDTO request) {
        Optional<User> user = userRepository.findById(request.userId());
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.get().getId().toString());
        }
        Issue issue = new Issue(request.title(), request.description(), user.get());
        issueRepository.save(issue);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
